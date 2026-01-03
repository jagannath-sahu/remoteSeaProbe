package com.dxc.remoteSeaProbe.autoconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * Scans auto-config classes
 * Evaluates conditions
 * Registers beans **only if conditions match**
 */

/*
 * `@ConditionalOnProperty` → user can disable it
 * `@ConditionalOnMissingBean` → user can override
 * `@EnableConfigurationProperties` → binds config
 */

/*
Auto-configuration allows conditional, opt-in bean creation based on classpath,
properties, and existing beans, enabling libraries to integrate seamlessly without explicit
configuration.
 */

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
