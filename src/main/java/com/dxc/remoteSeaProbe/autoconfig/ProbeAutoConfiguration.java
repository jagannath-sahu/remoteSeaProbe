package com.dxc.remoteSeaProbe.autoconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ProbeProperties.class)
@ConditionalOnProperty(prefix = "probe", name = "enabled", havingValue = "true", matchIfMissing = true)
public class ProbeAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ProbeClient probeClient(ProbeProperties properties) {
        return new ProbeClient(properties.getEndpoint());
    }
}
