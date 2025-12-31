package com.dxc.remoteSeaProbe.lifecycle;

import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Setter
@Component
public class DemoService implements InitializingBean {

    private String message;

    public DemoService() {
        System.out.println("DemoService Constructor called");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("DemoService afterPropertiesSet() called → message = " + message);
    }

    public void hello(){
        System.out.println("DemoService hello");
    }
}

