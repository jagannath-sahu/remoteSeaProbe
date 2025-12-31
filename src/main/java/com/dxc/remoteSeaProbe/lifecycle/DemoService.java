package com.dxc.remoteSeaProbe.lifecycle;

import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Setter
@Component
public class DemoService implements InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(DemoService.class);

    private String message;

    public DemoService() {
        log.info("DemoService Constructor called");
    }

    @Override
    public void afterPropertiesSet() {
        log.info("DemoService afterPropertiesSet() called → message = " + message);
    }

    public void hello(){
        log.info("DemoService hello");
    }
}

