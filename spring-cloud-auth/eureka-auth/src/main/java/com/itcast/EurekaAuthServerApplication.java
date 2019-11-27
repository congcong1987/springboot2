package com.itcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaAuthServerApplication {
	public static void main(String[] args) {
		System.out.println("begin springcloud eureka.....oauth2");
		SpringApplication.run(EurekaAuthServerApplication.class, args);
	}
}
