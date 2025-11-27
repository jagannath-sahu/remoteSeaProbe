package com.dxc.remoteSeaProbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class RemoteSeaProbeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RemoteSeaProbeApplication.class, args);
	}
}
