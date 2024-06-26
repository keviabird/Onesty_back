package com.onesty.services.search.configuration;

import com.onesty.services.search.configuration.kafka.CustomLoggingProducerListener;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.support.ProducerListener;

@Configuration
public class KafkaConfiguration {

    public static final String TOPIC_NAME = "match-topic";

    @Bean
    public ProducerListener<?, ?> producerListener() {
        return new CustomLoggingProducerListener();
    }

    @Bean
    public NewTopic matchTopic() {
        return TopicBuilder.name(TOPIC_NAME)
                .config(TopicConfig.RETENTION_MS_CONFIG, "86400000") // 1 day
                .config(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE)
                .config(TopicConfig.COMPRESSION_TYPE_CONFIG, "lz4")
                .partitions(3)
                // .replicas(3)
                .build();
    }

}