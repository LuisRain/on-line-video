package com.video.generate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@EnableAsync
public class GenerateApplication {

	public static void main(String[] args) {
		SpringApplication.run(GenerateApplication.class, args);
	}
}
