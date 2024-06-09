package com.example.velaassignment.entity;

import com.example.velaassignment.constants.CurrencyEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "PRODUCT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_quantity")
    private Integer quantity;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_price")
    private Integer price;

    @Enumerated(EnumType.STRING)
    private CurrencyEnum currencyEnum;

    @Column(name = "product_detail", length = 1000)
    private String detail;

    @Column(name = "created_date")
    private OffsetDateTime createdDate;

    @Column(name = "modified_date")
    private OffsetDateTime modifiedDate;

}
