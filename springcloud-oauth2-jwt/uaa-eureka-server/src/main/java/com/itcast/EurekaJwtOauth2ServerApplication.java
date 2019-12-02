package com.itcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer
public class EurekaJwtOauth2ServerApplication {
	public static void main(String[] args) {
		System.out.println("oauth2 jwt .....Eureka  server start.........");
		SpringApplication.run(EurekaJwtOauth2ServerApplication.class, args);
	}
}
