package com.wasp.springboot.publisher;

import com.wasp.springboot.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class RabbitMQJsonProducer {

    private final RabbitTemplate template;

    @Value("${spring.rabbitmq.exchange.name}")
    private String exchange;

    @Value("${spring.rabbitmq.routing.json.key}")
    private String routingJsonKey;

    public void sendMessage(User user) {
        template.convertAndSend(exchange, routingJsonKey, user);
        log.info(String.format("Json message sent -> %s", user));
    }


}
