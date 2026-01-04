package com.dxc.remoteSeaProbe.event;

import com.dxc.remoteSeaProbe.kafka.JsonProducer;
import com.dxc.remoteSeaProbe.kafka.SecurityMatchEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.dxc.remoteSeaProbe.kafka.DemoProducer;

import java.time.Instant;
import java.util.UUID;

@Component
public class StartupKafkaPublisher {

    private final DemoProducer demoProducer;

    private final JsonProducer jsonProducer;

    public StartupKafkaPublisher(DemoProducer demoProducer, JsonProducer jsonProducer) {
        this.demoProducer = demoProducer;
        this.jsonProducer = jsonProducer;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        demoProducer.send("Kafka: Application started successfully");
        jsonProducer.send(new SecurityMatchEvent(UUID.randomUUID().toString(),
                "security-1", "default",
                "default", "default", Instant.now()));
    }
}

