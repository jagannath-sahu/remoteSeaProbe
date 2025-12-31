package com.dxc.remoteSeaProbe.lifecycle;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DemoService2 {
    private static final Logger log = LoggerFactory.getLogger(DemoService2.class);

    public DemoService2() {
        log.info("DemoService2 constructor called");
    }

    @PostConstruct
    public void customInit() {
        log.info("DemoService2 @PostConstruct customInit() called");
    }
}

