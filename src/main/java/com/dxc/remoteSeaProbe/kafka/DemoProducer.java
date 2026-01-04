package com.dxc.remoteSeaProbe.kafka;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class DemoProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public DemoProducer(@Qualifier("stringKafkaTemplate") KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String message) {
        kafkaTemplate.send("demo-topic", message);
    }
}

