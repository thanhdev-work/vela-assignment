package com.example.velaassignment.payload.res;

import com.example.velaassignment.constants.CurrencyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private Integer quantity;
    private Integer price;
    private CurrencyEnum currencyEnum;
    private String detail;
    private String createdDate;
    private String modifiedDate;
}
