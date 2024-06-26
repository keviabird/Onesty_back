package com.onesty.services.search.configuration.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;

@Slf4j
public class CustomLoggingProducerListener implements ProducerListener<Object, Object> {

    @Override
    public void onSuccess(ProducerRecord<Object, Object> record, RecordMetadata metadata) {
        log.info("Successfully sent record with key {} to topic {} {}-{}",
                record.key(), metadata.topic(), metadata.partition(), metadata.offset());
    }

    @Override
    public void onError(ProducerRecord<Object, Object> record, RecordMetadata metadata, Exception exception) {
        log.error("Failed to send record with key {} to topic {} {}-{}. {}",
                record.key(), metadata.topic(), metadata.partition(), metadata.offset(), exception.getMessage(), exception);
    }
}