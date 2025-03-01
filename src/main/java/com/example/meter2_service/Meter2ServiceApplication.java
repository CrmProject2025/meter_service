package com.example.meter2_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Meter2ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Meter2ServiceApplication.class, args);
	}

}
