package com.dxc.remoteSeaProbe;

import com.dxc.remoteSeaProbe.autoconfig.ProbeClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("local")
public class ProbeTestRunner implements CommandLineRunner {

    private final ProbeClient probeClient;

    public ProbeTestRunner(ProbeClient probeClient) {
        this.probeClient = probeClient;
    }

    @Override
    public void run(String... args) {
        System.out.println(probeClient.ping());
    }
}

