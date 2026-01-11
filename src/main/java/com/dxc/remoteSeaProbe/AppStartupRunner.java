package com.dxc.remoteSeaProbe;

import com.dxc.remoteSeaProbe.lifecycle.ContextAwareBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.function.Supplier;

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

        //Supplier<T> → gives T
        /*
        Supplier is a functional interface that provides values lazily without taking input.
        It’s commonly used to defer expensive computations, such as in Optional.orElseGet or cache loading.
         */
        Supplier<LocalDate> today = LocalDate::now;
        log.info("today time: {}", today.get());
        log.info("today time: {}", today.get());//different from above
        Supplier<Double> randomValue = Math::random;
        log.info("randomValue: {}", randomValue);
        Supplier<String> supplier = () -> "Hello";
        log.info("supplier lambda: {}", supplier.get());



        log.info("AppStartupRunner end ...");
    }
}

