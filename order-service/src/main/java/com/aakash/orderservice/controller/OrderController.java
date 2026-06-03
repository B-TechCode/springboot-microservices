package com.aakash.orderservice.controller;

import com.aakash.orderservice.common.Payment;
import com.aakash.orderservice.common.TransactionRequest;
import com.aakash.orderservice.common.TransactionResponse;
import com.aakash.orderservice.entity.Order;
import com.aakash.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("/bookOrder")
    public TransactionResponse bookOrder(@RequestBody TransactionRequest request){

        return service.saveOrder(request);

    }
}
