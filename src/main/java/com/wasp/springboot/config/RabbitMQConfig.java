package com.wasp.springboot.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.queue.name}")
    private String queue;

    @Value("${spring.rabbitmq.queue.json.name}")
    private String queueJson;

    @Value("${spring.rabbitmq.exchange.name}")
    private String exchange;

    @Value("${spring.rabbitmq.routing.key}")
    private String routingKey;

    @Value("${spring.rabbitmq.routing.json.key}")
    private String routingJsonKey;

    // spring bean for RabbitMQ queue
    @Bean
    public Queue queue() {
        return new Queue(queue);
    }

    @Bean
    public Queue queueJson() {
        return new Queue(queueJson);
    }

    //spring bean for RabbitMQ exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    //binding queue to exchange using routing key
    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(routingKey);
    }

    @Bean
    public Binding bindingJson() {
        return BindingBuilder
                .bind(queueJson())
                .to(exchange())
                .with(routingJsonKey);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connection) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connection);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

    // Springboot will automatically resolve the 3 below beans implicitly
    // ConnectionFactory
    // RabbitTemplate
    // RabbitAdmin
}
