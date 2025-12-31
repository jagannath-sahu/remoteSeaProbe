package com.dxc.remoteSeaProbe.lifecycle;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyCustomBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if (bean instanceof DemoService) {
            System.out.println("BeanPostProcessor BEFORE init → " + beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (bean instanceof DemoService) {
            System.out.println("BeanPostProcessor AFTER init → " + beanName);
        }
        return bean;
    }
}

