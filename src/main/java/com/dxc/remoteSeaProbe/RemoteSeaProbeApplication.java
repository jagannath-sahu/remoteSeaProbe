package com.dxc.remoteSeaProbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties
@EnableScheduling
public class RemoteSeaProbeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RemoteSeaProbeApplication.class, args);
	}
}
