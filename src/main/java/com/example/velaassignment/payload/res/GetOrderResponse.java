package com.example.velaassignment.payload.res;

import com.example.velaassignment.entity.Product;
import com.example.velaassignment.utils.DateTimeUtils;
import com.sun.scenario.effect.Offset;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GetOrderResponse {
    private Long orderId;
    private String customerName;
    private String address;
    private String phone;
    private String email;
    private String orderStatus;
    private String createdDate;
    private String modifiedDate;
    private List<Product> orderProducts;

    public GetOrderResponse(Long orderId, String customerName, String address, String phone, String email, String orderStatus, OffsetDateTime createdDate, OffsetDateTime modifiedDate, List<Product> orderProducts) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.orderStatus = orderStatus;
        this.createdDate = DateTimeUtils.format(createdDate);
        this.modifiedDate = DateTimeUtils.format(modifiedDate);
        this.orderProducts = orderProducts;
    }

}
