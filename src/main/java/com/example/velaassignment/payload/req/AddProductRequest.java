package com.example.velaassignment.payload.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddProductRequest {

    @NotBlank(message = "40001")
    private String name;

    private String detail;

    @Min(value = 0, message = "40002")
    @NotNull(message = "40001")
    private Integer quantity;

    @Min(value = 0, message = "40003")
    @NotNull(message = "40001")
    private Integer price;
}
