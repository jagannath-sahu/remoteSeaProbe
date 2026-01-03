package com.dxc.remoteSeaProbe.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class DemoConsumer {
    private static final Logger log = LoggerFactory.getLogger(DemoConsumer.class);

    @KafkaListener(topics = "demo-topic", groupId = "remote-sea-probe-group")
    public void consume(
            String message,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(KafkaHeaders.OFFSET) long offset
    ) {
        log.info("Consumed message='{}' partition={} offset={}", message, partition, offset);
    }
}

