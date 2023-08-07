package com.example.orderservice.publisher;

import com.example.orderservice.dto.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMQProducer {


    @Value("${rabbitmq.queue.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.queue.order.key}")
    private String orderKey;

    @Value("${rabbitmq.queue.email.key}")
    private String emailKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);
    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(OrderEvent orderEvent){
        LOGGER.info(String.format("Order event sent to RabbitMQ OrderQ => %s",orderEvent.toString()));
        rabbitTemplate.convertAndSend(exchange,orderKey,orderEvent);
        LOGGER.info(String.format("Order event sent to RabbitMQ EmailQ => %s",orderEvent.toString()));
        rabbitTemplate.convertAndSend(exchange,emailKey,orderEvent);
    }
}
