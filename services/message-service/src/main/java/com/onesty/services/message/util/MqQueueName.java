package com.onesty.services.message.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("QueueName")
public class MqQueueName {

    public static final String QUEUE_NAME = UUID.randomUUID().toString();
}
