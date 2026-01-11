package com.dxc.remoteSeaProbe.kafka.dlq;

import com.dxc.remoteSeaProbe.kafka.SecurityMatchEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SecurityMatchDLQListener {

    @KafkaListener(
            topics = "security-match.event.v1.dlq",
            groupId = "security-match-dlq-group",
            containerFactory = "jsonKafkaListenerContainerFactory"
    )
    public void consumeFromDlq(
            SecurityMatchEvent event,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(KafkaHeaders.OFFSET) long offset) {

        log.error(
                "DLQ EVENT -> topic={}, partition={}, offset={}, payload={}",
                topic, partition, offset, event
        );

        // store to DB / send alert / mark for replay
    }
}

