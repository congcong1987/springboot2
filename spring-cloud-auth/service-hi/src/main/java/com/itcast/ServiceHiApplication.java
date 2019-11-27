package com.itcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServiceHiApplication {
	public static void main(String[] args) {
		System.out.println("oauht2  service hi");
		SpringApplication.run(ServiceHiApplication.class, args);
	}
}
