package com.dxc.remoteSeaProbe;

import com.dxc.remoteSeaProbe.lifecycle.ContextAwareBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class AppStartupRunner implements ApplicationRunner {
    private static final Logger log = LoggerFactory.getLogger(AppStartupRunner.class);
    private final ContextAwareBean contextAwareBean;

    public AppStartupRunner(ContextAwareBean contextAwareBean) {
        this.contextAwareBean = contextAwareBean;
    }

    @Override
    public void run(ApplicationArguments args) {
        log.info("AppStartupRunner started ...");
        contextAwareBean.showAllBeans();
        log.info("AppStartupRunner end ...");
    }
}

