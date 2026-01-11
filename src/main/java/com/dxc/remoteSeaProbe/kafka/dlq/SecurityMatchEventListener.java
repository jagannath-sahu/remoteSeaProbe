package com.dxc.remoteSeaProbe.kafka.dlq;

import com.dxc.remoteSeaProbe.kafka.SecurityMatchEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SecurityMatchEventListener {

    @KafkaListener(
            topics = "security-match.event.v1",
            groupId = "security-match-group",
            containerFactory = "jsonKafkaListenerContainerFactory"
    )
    public void consume(SecurityMatchEvent event) {

        log.info("Consumed event: {}", event);

        // Simulate failure
        if ("security-1".equals(event.getSecurityId())) {
            throw new RuntimeException("Simulated processing failure");
        }

        log.info("Processed successfully");
    }
}

