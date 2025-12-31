package com.dxc.remoteSeaProbe;

import com.dxc.remoteSeaProbe.lifecycle.ContextAwareBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppStartupRunner implements CommandLineRunner {

    private final ContextAwareBean contextAwareBean;

    public AppStartupRunner(ContextAwareBean contextAwareBean) {
        this.contextAwareBean = contextAwareBean;
    }

    @Override
    public void run(String... args) {
        System.out.println("AppStartupRunner start ...");
        contextAwareBean.showAllBeans();
        System.out.println("AppStartupRunner end ...");
    }
}

