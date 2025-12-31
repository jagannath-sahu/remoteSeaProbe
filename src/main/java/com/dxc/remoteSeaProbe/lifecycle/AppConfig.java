package com.dxc.remoteSeaProbe.lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public DemoService demoService() {
        DemoService service = new DemoService();
        service.setMessage("Hello World");
        return service;
    }
}

