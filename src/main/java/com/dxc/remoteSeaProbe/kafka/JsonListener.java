package com.dxc.remoteSeaProbe.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class JsonListener {
    private static final Logger log = LoggerFactory.getLogger(JsonListener.class);

    @KafkaListener(
            topics = "demo-topic-v2",
            containerFactory = "jsonKafkaListenerContainerFactory"
    )
    public void consume(SecurityMatchEvent event) {
        log.info("Consumed event: {}", event);
    }

}
