package com.example.orderservice.controller;

import com.example.orderservice.dto.Order;
import com.example.orderservice.dto.OrderEvent;
import com.example.orderservice.publisher.RabbitMQProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class OrderController {

    private final RabbitMQProducer rabbitMQProducer;


    @PostMapping("/orders")
    public ResponseEntity<String> placeOrder(@RequestBody Order order){
        order.setOrderId(UUID.randomUUID().toString());
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("Order is in pending status");
        orderEvent.setOrder(order);

        rabbitMQProducer.sendMessage(orderEvent);
        return ResponseEntity.ok("Order sent to the RabbitMQ");
    }
}
