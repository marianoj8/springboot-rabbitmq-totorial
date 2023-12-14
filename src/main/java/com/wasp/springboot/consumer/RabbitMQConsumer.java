package com.wasp.springboot.consumer;

import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class RabbitMQConsumer {

    @RabbitListener(queues = {"${spring.rabbitmq.queue.name}"})
    public void consumer(String message){
        log.info(String.format("Received message -> %s", message));
    }
}
