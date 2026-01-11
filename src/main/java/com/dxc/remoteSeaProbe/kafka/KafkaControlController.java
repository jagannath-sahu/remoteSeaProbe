package com.dxc.remoteSeaProbe.kafka;

import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaControlController {

    private final KafkaListenerEndpointRegistry registry;

    public KafkaControlController(KafkaListenerEndpointRegistry registry) {
        this.registry = registry;
    }

    @PostMapping("/pause")
    public String pause() {
        registry.getListenerContainers()
                .forEach(MessageListenerContainer::pause);
        return "Kafka listeners paused";
    }

    @PostMapping("/resume")
    public String resume() {
        registry.getListenerContainers()
                .forEach(MessageListenerContainer::resume);
        return "Kafka listeners resumed";
    }
}

