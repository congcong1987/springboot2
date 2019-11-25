package com.itcast;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@MapperScan(basePackages = "com.itcast.mapper")
public class SpringRedisCacheApplication {
	
	public static void main(String[] args) {
		
		System.out.println("springboot***缓存redis。。。。。");
		SpringApplication.run(SpringRedisCacheApplication.class, args);
	}

}
