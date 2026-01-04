package com.dxc.remoteSeaProbe.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class JsonProducer {
    private static final Logger log = LoggerFactory.getLogger(JsonProducer.class);
    private final KafkaTemplate<String, Object> jsonKafkaTemplate;

    public JsonProducer(@Qualifier("jsonKafkaTemplate") KafkaTemplate<String, Object> jsonKafkaTemplate) {
        this.jsonKafkaTemplate = jsonKafkaTemplate;
    }

    public void send(SecurityMatchEvent event) {
        log.info("Producing eventId={}", event.getEventId());
        jsonKafkaTemplate.send("demo-topic-v2",
                event.getEventId(), //🔑 custom key, Order guaranteed per security and Same securityId → same partition
                event);
    }
}

