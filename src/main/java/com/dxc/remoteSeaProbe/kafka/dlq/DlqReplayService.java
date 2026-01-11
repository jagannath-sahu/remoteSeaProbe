package com.dxc.remoteSeaProbe.kafka.dlq;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.dxc.remoteSeaProbe.kafka.SecurityMatchEvent;

@Service
@RequiredArgsConstructor
public class DlqReplayService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void replay(SecurityMatchEvent event) {
        kafkaTemplate.send(
                "security-match.event.v1",
                event.getEventId(),
                event
        );
    }
}

