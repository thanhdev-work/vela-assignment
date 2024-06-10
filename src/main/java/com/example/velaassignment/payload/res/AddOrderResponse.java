package com.example.velaassignment.payload.res;

import com.example.velaassignment.constants.OrderStatusEnum;
import com.example.velaassignment.entity.OrderDetail;
import com.example.velaassignment.payload.req.AddOrderRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddOrderResponse {
    private Long orderId;
    private String customerName;
    private String address;
    private String phone;
    private String email;
    private OrderStatusEnum orderStatusEnum;
    private List<OrderDetail> orderDetail;
    private List<AddOrderRequest.OrderProduct> orderProduct;
}
