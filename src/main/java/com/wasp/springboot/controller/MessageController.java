package com.wasp.springboot.controller;

import com.wasp.springboot.dto.User;
import com.wasp.springboot.publisher.RabbitMQJsonProducer;
import com.wasp.springboot.publisher.RabbitMQProducer;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/")
@AllArgsConstructor
public class MessageController {

    private RabbitMQProducer producer;
    private RabbitMQJsonProducer jsonProducer;

    @GetMapping("publish")
    public ResponseEntity<String> sendMessage(@RequestParam String message){
        producer.sendMessage(message);
        return ResponseEntity.ok("Message sent to RabbitMQ...");
    }

    @PostMapping("publish/json")
    public ResponseEntity<String> sendMessage(@RequestBody User user){
        jsonProducer.sendMessage(user);
        return ResponseEntity.ok("Json Message Sent to RabbitMQ...");
    }
}
