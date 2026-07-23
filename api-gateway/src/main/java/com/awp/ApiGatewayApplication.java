package com.awp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

	public static void main(String[] args) {

		System.out.println("Hello, I am API-GATEWAY");
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
