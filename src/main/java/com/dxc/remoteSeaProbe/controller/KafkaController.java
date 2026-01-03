package com.dxc.remoteSeaProbe.controller;

import com.dxc.remoteSeaProbe.kafka.DemoProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    private final DemoProducer producer;

    public KafkaController(DemoProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/send")
    public String send(@RequestParam String msg) {
        producer.send(msg);
        return "sent";
    }
}

