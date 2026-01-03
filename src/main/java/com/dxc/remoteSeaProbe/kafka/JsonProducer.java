package com.dxc.remoteSeaProbe.kafka;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class JsonProducer {

    private final KafkaTemplate<String, Object> jsonKafkaTemplate;

    public JsonProducer(@Qualifier("jsonKafkaTemplate") KafkaTemplate<String, Object> jsonKafkaTemplate) {
        this.jsonKafkaTemplate = jsonKafkaTemplate;
    }

    public void send(SecurityMatchEvent event) {
        jsonKafkaTemplate.send("demo-topic-v2", event.getEventId(), event);
    }
}

