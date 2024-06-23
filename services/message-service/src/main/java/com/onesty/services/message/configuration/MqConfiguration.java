package com.onesty.services.message.configuration;


import com.onesty.services.message.util.MqQueueName;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class MqConfiguration {
    private final String queueName = UUID.randomUUID().toString();
    private final String exchangeName = "chatMessageSseExchange";


    @Bean
    public Queue myQueue(){
        return new Queue(MqQueueName.QUEUE_NAME, false);
    }

    @Bean
    Exchange exchange(){
        return new TopicExchange(exchangeName, false, false);
    }

}
