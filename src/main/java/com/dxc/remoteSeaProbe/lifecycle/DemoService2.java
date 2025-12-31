package com.dxc.remoteSeaProbe.lifecycle;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DemoService2 {

    public DemoService2() {
        System.out.println("DemoService2 constructor called");
    }

    @PostConstruct
    public void customInit() {
        System.out.println("DemoService2 @PostConstruct customInit() called");
    }
}

