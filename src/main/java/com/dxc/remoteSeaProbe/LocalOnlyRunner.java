package com.dxc.remoteSeaProbe;

import com.dxc.remoteSeaProbe.sample.SingletonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@Profile("local")
public class LocalOnlyRunner implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(LocalOnlyRunner.class);
    private final SingletonService singletonService;

    public LocalOnlyRunner(SingletonService singletonService) {
        this.singletonService = singletonService;
    }

    @Override
    public void run(String... args) {
        log.info("LocalOnlyRunner singletonService: {}",  singletonService.printHash());
    }
}

