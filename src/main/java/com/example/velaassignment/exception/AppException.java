package com.example.velaassignment.exception;

import lombok.Getter;

public class AppException extends RuntimeException {
    @Getter
    private String message;

    @Getter
    private int code;

    public AppException(String message, int code) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public AppException(String message) {
        super(message);
        this.message = message;
    }

    public AppException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.message = exceptionEnum.getMessage();
        this.code = exceptionEnum.getCode();
    }

}
