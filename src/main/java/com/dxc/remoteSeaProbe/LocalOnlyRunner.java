package com.dxc.remoteSeaProbe;

import com.dxc.remoteSeaProbe.test.SingletonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("local")
public class LocalOnlyRunner implements CommandLineRunner {
    private final SingletonService singletonService;

    public LocalOnlyRunner(SingletonService singletonService) {
        this.singletonService = singletonService;
    }

    @Override
    public void run(String... args) {
        System.out.println("LocalOnlyRunner singletonService: " + singletonService.printHash());
    }
}

