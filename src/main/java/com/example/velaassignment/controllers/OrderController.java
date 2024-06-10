package com.example.velaassignment.controllers;

import com.example.velaassignment.helper.validator.ServiceValidate;
import com.example.velaassignment.payload.req.AddOrderRequest;
import com.example.velaassignment.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/emc/v1/order")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    @ServiceValidate
    public ResponseEntity<?> addOrder(@Valid @RequestBody AddOrderRequest addOrderRequest, BindingResult bindingResult) {
        return ok(orderService.addOrder(addOrderRequest));
    }

    @GetMapping("/")
    public ResponseEntity<?> getOrder(@RequestParam(name = "id") Long id) {
        return ok(orderService.getOrder(id));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteOrder(@RequestParam(name = "id") Long id) {
        return null;
    }

    @PutMapping
    public ResponseEntity<?> updateOrder(@Valid @RequestBody AddOrderRequest addOrderRequest, BindingResult bindingResult) {
        return null;
    }
}
