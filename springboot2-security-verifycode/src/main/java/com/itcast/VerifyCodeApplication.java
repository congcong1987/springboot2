package com.itcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VerifyCodeApplication {
	
	public static void main(String[] args) {
		System.out.println("校验码验证**********");
		SpringApplication.run(VerifyCodeApplication.class, args);
	}

}
