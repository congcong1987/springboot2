package com.itcast;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.SocketUtils;

@SpringBootApplication
@MapperScan(basePackages = "com.itcast.mapper")
public class SecurityDyApplication {
	
	public static void main(String[] args) {
		System.out.println("spring boot secutiry");
		
		/* AntPathMatcher pathMatcher = new AntPathMatcher(); //spring 匹配路径规则
		 System.out.println(pathMatcher.match("tes?", "test")+"--------");*/
		
		SpringApplication.run(SecurityDyApplication.class, args);
		
		
		
		
	}

}
