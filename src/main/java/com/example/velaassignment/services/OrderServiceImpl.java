package com.example.velaassignment.services;

import com.example.velaassignment.constants.OrderStatusEnum;
import com.example.velaassignment.entity.Order;
import com.example.velaassignment.entity.OrderDetail;
import com.example.velaassignment.exception.AppException;
import com.example.velaassignment.exception.ExceptionEnum;
import com.example.velaassignment.payload.req.AddOrderRequest;
import com.example.velaassignment.payload.res.AddOrderResponse;
import com.example.velaassignment.payload.res.GetOrderResponse;
import com.example.velaassignment.repository.OrderDetailRepository;
import com.example.velaassignment.repository.OrderRepository;
import com.example.velaassignment.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductService productService;

    @Override
    @Transactional(rollbackFor = {AppException.class, RuntimeException.class}, propagation = Propagation.REQUIRES_NEW)
    public AddOrderResponse addOrder(AddOrderRequest addOrderRequest) {
        if (addOrderRequest.getProducts().isEmpty())
            throw new AppException(ExceptionEnum.ORDER_ADD_LIST_PRODUCT_IS_EMPTY);

        Order var1 = new Order(null, addOrderRequest.getCusName(), addOrderRequest.getAddress(), addOrderRequest.getPhone(),
                addOrderRequest.getEmail(), OrderStatusEnum.PENDING, OffsetDateTime.now(), null);

        Order order = orderRepository.save(var1);
        List<OrderDetail> orderDetails = new ArrayList<>();
        addOrderRequest.getProducts().forEach(e -> {
            productService.updateQuantity(e.getId(), e.getQuantity());
            orderDetails.add(new OrderDetail(null, order.getId(), e.getId(), OffsetDateTime.now(), null));
        });

        List<OrderDetail> var2 = orderDetailRepository.saveAll(orderDetails);

        return new AddOrderResponse(order.getId(), order.getCustomerName(), order.getAddress(), order.getPhoneNumber(), order.getEmail(), order.getOrderStatus(), var2, addOrderRequest.getProducts());
    }

    @Override
    public GetOrderResponse getOrder(Long id) {
        orderRepository.getOrderById(id);
        if (orderRepository.getOrderById(id).isPresent()) {
            return orderRepository.getOrderById(id).get();
        } else {
            throw new AppException(ExceptionEnum.ORDER_NOT_FOUND);
        }
    }

    @Override
    public void deleteOrder() {

    }

    @Override
    public void updateOrder() {

    }
}
