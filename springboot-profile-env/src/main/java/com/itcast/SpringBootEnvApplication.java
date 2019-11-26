package com.itcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:${spring.profiles.active}/spring-context.xml"})
@ComponentScan("com.itcast")
public class SpringBootEnvApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootEnvApplication.class, args);
	}

}
