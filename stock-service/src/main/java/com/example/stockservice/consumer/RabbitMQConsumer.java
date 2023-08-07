package com.example.stockservice.consumer;

import com.example.stockservice.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @RabbitListener(queues = "${rabbitmq.queue.order.name}")
    public void consume(OrderEvent orderEvent){
        LOGGER.info(String.format("Order event received in stock service -> %s", orderEvent.toString()));


    }
}
