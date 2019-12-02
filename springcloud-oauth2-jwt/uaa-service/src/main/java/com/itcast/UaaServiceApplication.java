package com.itcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;



/**
 * 
 * http://127.0.0.1:9999/oauth/token  ==>获取token
 * 
 * @author DELL
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class UaaServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(UaaServiceApplication.class, args);
	}
}
