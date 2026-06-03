package com.aakash.orderservice.service;

import com.aakash.orderservice.common.Payment;
import com.aakash.orderservice.common.TransactionRequest;
import com.aakash.orderservice.common.TransactionResponse;
import com.aakash.orderservice.entity.Order;
import com.aakash.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private RestTemplate template;

    public TransactionResponse saveOrder(TransactionRequest request){
        String response="";
        Order order=request.getOrder();
        Payment payment=request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());

        //rest call
   Payment paymentResponse= template.postForObject("http://localhost:8082/payment/doPayment",payment,Payment.class);


  response= paymentResponse.getPaymentStatus().equals("sucess")?"payment processing successful and order placed": "there is a failure in payment api, order added to cart";


         repository.save(order);
         return new TransactionResponse(order,paymentResponse.getAmount(),paymentResponse.getTransactionId(),response);
    }
}
