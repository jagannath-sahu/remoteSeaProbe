package com.dxc.remoteSeaProbe;

import com.dxc.remoteSeaProbe.autoconfig.ProbeClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("local")
public class ProbeTestRunner implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(ProbeTestRunner.class);

    private final ProbeClient probeClient;

    public ProbeTestRunner(ProbeClient probeClient) {
        this.probeClient = probeClient;
    }

    @Override
    public void run(String... args) {
        log.info("ProbeTestRunner : " + probeClient.ping());
    }
}

