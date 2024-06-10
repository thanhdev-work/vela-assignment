package com.example.velaassignment.payload.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddOrderRequest {
    @NotBlank(message = "40003")
    private String cusName;

    @Pattern(regexp = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}", message = "40004")
    private String phone;

    @Email(message = "40005")
    private String email;

    @NotEmpty(message = "40006")
    private String address;

    private List<OrderProduct> products;


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderProduct {
        private Long id;
        private Integer quantity;
    }
}
