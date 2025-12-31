package com.dxc.remoteSeaProbe.lifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyCustomBeanPostProcessor implements BeanPostProcessor {
    private static final Logger log = LoggerFactory.getLogger(MyCustomBeanPostProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if (bean instanceof DemoService) {
            log.info("BeanPostProcessor BEFORE init → " + beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (bean instanceof DemoService) {
            log.info("BeanPostProcessor AFTER init → " + beanName);
        }
        return bean;
    }
}

