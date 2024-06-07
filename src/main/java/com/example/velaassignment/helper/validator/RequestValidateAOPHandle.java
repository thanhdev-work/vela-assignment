package com.example.velaassignment.helper.validator;

import com.example.velaassignment.controllers.BaseController;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.math.NumberUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Aspect
@Component
@PropertySource(
        value = "classpath:messages/message.properties",
        encoding = "UTF-8",
        name = "errorMessage"
)
public class RequestValidateAOPHandle extends BaseController {
    @Autowired
    private Environment env;

    @Around("execution(* *(..)) && @annotation(validateAnnotation)")
    public Object validateAnnotation(
            ProceedingJoinPoint point, ServiceValidate validateAnnotation)
            throws Throwable {
        // Get dataRequest
        BindingResult bindingResult = null;

        //Get BindingResult parameter
        for (Object arg : point.getArgs()) {
            if (arg instanceof BindingResult) {
                bindingResult = (BindingResult) arg;
            }
        }
        if (bindingResult == null) {
            point.proceed();
        }
        org.springframework.core.env.PropertySource<?> errorCodeSource =
                ((AbstractEnvironment) env).getPropertySources().get("errorMessage");
        Properties props = new Properties();
        if (errorCodeSource != null) {
            props = (Properties) errorCodeSource.getSource();
        }
        if (bindingResult.hasErrors()) {
            List<InvalidProperties> invalidProperties = new ArrayList<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                String message = fieldError.getDefaultMessage();
                if (NumberUtils.isDigits(message)) {
                    message = props.getProperty(message);
                }
                invalidProperties.add(new InvalidProperties(fieldError.getField(), message));
            }
            return badReq(invalidProperties);
        } else {
            return point.proceed();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    static class InvalidProperties {
        private String property;
        private String message;
    }
}
