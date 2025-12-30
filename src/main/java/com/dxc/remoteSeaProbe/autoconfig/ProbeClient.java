package com.dxc.remoteSeaProbe.autoconfig;

public class ProbeClient {

    private final String endpoint;

    public ProbeClient(String endpoint) {
        this.endpoint = endpoint;
    }

    public String ping() {
        return "Pinging " + endpoint;
    }
}

