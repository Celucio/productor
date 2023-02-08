package com.example.productor.controller;

import com.example.productor.modelo.Message;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Producer {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private DirectExchange exchange;
    @Autowired
    private FanoutExchange fanoutExchange;

    @Autowired
    private TopicExchange topicExchange;

    @PostMapping("/post")
    public String send(@RequestBody Message message){
        rabbitTemplate.convertAndSend(exchange.getName(), "routing.A", message);
        return "Message send successfully";
    }
    @PostMapping("/postB")
    public String sendB(@RequestBody Message message){
        rabbitTemplate.convertAndSend(exchange.getName(), "routing.B", message);
        return "Message send successfully";
    }
    /*
    @PostMapping("/postC")
    public String sendC(@RequestBody Message message){
        rabbitTemplate.convertAndSend(exchange.getName(), "routing.C", message);
        return "Message send successfully";
    }
    @PostMapping("/postD")
    public String sendD(@RequestBody Message message){
        rabbitTemplate.convertAndSend(exchange.getName(), "routing.D", message);
        return "Message send successfully";
    }
    */
    @PostMapping("/broadcast")
    public String sendMasivo(@RequestBody Message message){
        rabbitTemplate.convertAndSend(fanoutExchange.getName(),"", message);
        return "Message send successfully broadcast";
    }

    @PostMapping("/postAT")
    public String sendATopic(@RequestBody Message message){
        rabbitTemplate.convertAndSend(topicExchange.getName(),"routing.A", message);
        return "Message send successfully topic A";
    }

    @PostMapping("/postBT")
    public String sendBTopic(@RequestBody Message message){
        rabbitTemplate.convertAndSend(topicExchange.getName(),"routing.B", message);
        return "Message send successfully topic B";
    }

    @PostMapping("/postALLT")
    public String sendALLTopic(@RequestBody Message message){
        rabbitTemplate.convertAndSend(topicExchange.getName(),"routing.*", message);
        return "Message send successfully topic ALL";
    }



}
