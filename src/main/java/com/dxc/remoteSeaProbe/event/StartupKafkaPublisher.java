package com.dxc.remoteSeaProbe.event;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.dxc.remoteSeaProbe.kafka.DemoProducer;

@Component
public class StartupKafkaPublisher {

    private final DemoProducer demoProducer;

    public StartupKafkaPublisher(DemoProducer demoProducer) {
        this.demoProducer = demoProducer;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        demoProducer.send("Kafka: Application started successfully");
    }
}

