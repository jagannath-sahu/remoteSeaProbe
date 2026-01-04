package com.dxc.remoteSeaProbe.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class StringProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public StringProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String msg) {
        kafkaTemplate.send("demo-topic", msg);
    }
}

