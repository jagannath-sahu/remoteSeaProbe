package com.dxc.remoteSeaProbe.lifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ContextAwareBean implements ApplicationContextAware {
    private static final Logger log = LoggerFactory.getLogger(ContextAwareBean.class);
    private ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        log.info("setApplicationContext called");
        this.ctx = applicationContext;
    }

    public void showAllBeans() {
        log.info("Total Beans Loaded: {}", ctx.getBeanDefinitionCount());
        DemoService service = ctx.getBean(DemoService.class);
        service.hello();
    }
}

