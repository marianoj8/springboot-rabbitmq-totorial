package com.wasp.springboot.consumer;

import com.wasp.springboot.dto.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
@Log4j2
@Service
public class RabbitMQJsonConsumer {

    @RabbitListener(queues = "${spring.rabbitmq.queue.json.name}")
    public void consumer(User user){
        log.info(String.format("Received json message -> %s", user));
    }
}
