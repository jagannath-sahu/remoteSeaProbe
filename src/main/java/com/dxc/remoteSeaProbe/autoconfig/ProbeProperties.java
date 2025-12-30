package com.dxc.remoteSeaProbe.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "probe")
public class ProbeProperties {

    //If no external configuration provides probe.endpoint, then use this default.(fallback)
    private String endpoint = "http://localhost:9999";

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
