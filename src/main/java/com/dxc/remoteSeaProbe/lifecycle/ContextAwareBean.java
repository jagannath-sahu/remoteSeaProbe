package com.dxc.remoteSeaProbe.lifecycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ContextAwareBean implements ApplicationContextAware {

    private ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        System.out.println("setApplicationContext() called");
        this.ctx = applicationContext;
    }

    public void showAllBeans() {
        System.out.println("Total Beans Loaded: " + ctx.getBeanDefinitionCount());
        DemoService service = ctx.getBean(DemoService.class);
        service.hello();
    }
}

