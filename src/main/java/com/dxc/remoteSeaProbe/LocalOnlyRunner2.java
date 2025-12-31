package com.dxc.remoteSeaProbe;

import com.dxc.remoteSeaProbe.sample.SingletonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("local")
public class LocalOnlyRunner2 implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(LocalOnlyRunner2.class);
    private final SingletonService singletonService;

    public LocalOnlyRunner2(SingletonService singletonService) {
        this.singletonService = singletonService;
    }

    @Override
    public void run(String... args) {
        log.info("LocalOnlyRunner2 singletonService: " + singletonService.printHash());
    }
}


