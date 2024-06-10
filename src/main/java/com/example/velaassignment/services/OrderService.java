package com.example.velaassignment.services;

import com.example.velaassignment.payload.req.AddOrderRequest;
import com.example.velaassignment.payload.res.AddOrderResponse;
import com.example.velaassignment.payload.res.GetOrderResponse;

public interface OrderService {
    AddOrderResponse addOrder(AddOrderRequest addOrderRequest);

    GetOrderResponse getOrder(Long id);

    void deleteOrder();

    void updateOrder();
}
