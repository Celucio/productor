package com.example.productor.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigRabbitMQ {

    public static final String QUEUE_A = "queue.A";
    public static final String QUEUE_B = "queue.B";
    public static final String ROUTING_A = "routing.A";
    public static final String ROUTING_B = "routing.B";
    /*
    public static final String QUEUE_C = "queue.C";
    public static final String QUEUE_D = "queue.D";
    public static final String ROUTING_C = "routing.C";
    public static final String ROUTING_D = "routing.D";

     */
    @Bean
    Queue queueA(){
        return new Queue(QUEUE_A, false);
    }

    @Bean
    Queue queueB(){
        return new Queue(QUEUE_B, false);
    }
    /*
    @Bean
    Queue queueC(){
        return new Queue(QUEUE_C, false);
    }

    @Bean
    Queue queueD(){
        return new Queue(QUEUE_D, false);
    }
    */

    @Bean
    DirectExchange exchange(){
        return new DirectExchange("exchange.direct");
    }

    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange("exchange.fanout");
    }

    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange("exchange.topic");
    }

    @Bean
    Binding bindingA(Queue queueA, DirectExchange exchange){
        return BindingBuilder.bind(queueA).to(exchange).with(ROUTING_A);
    }

    @Bean
    Binding bindingB(Queue queueB, DirectExchange exchange){
        return BindingBuilder.bind(queueB).to(exchange).with(ROUTING_B);
    }
    /*
    @Bean
    Binding bindingC(Queue queueC, DirectExchange exchange){
        return BindingBuilder.bind(queueC).to(exchange).with(ROUTING_C);
    }

    @Bean
    Binding bindingD(Queue queueD, DirectExchange exchange){
        return BindingBuilder.bind(queueD).to(exchange).with(ROUTING_D);
    }
    */

    @Bean
    Binding bindingFanoutA(Queue queueA, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queueA).to(fanoutExchange);
    }

    @Bean
    Binding bindingFanoutB(Queue queueB, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queueB).to(fanoutExchange);
    }
    //Exchange Topic
    @Bean
    Binding bindingTopicA(Queue queueA, TopicExchange topicExchange){
        return BindingBuilder.bind(queueA).to(topicExchange).with(ROUTING_A);
    }
    @Bean
    Binding bindingTopicB(Queue queueB, TopicExchange topicExchange){
        return BindingBuilder.bind(queueB).to(topicExchange).with(ROUTING_B);
    }

    /*
    @Bean
    Binding bindingFanoutC(Queue queueC, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queueC).to(fanoutExchange);
    }

    @Bean
    Binding bindingFanoutD(Queue queueD, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queueD).to(fanoutExchange);
    }
    */
    @Bean
    MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory factory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

}
