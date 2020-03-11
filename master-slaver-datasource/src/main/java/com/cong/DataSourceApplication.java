package com.cong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class DataSourceApplication {
	
	
	public static void main(String[] args) {
//		ApplicationContext applicationContext = SpringApplication.run(DataSourceApplication.class, args);
		
		SpringApplication application = new SpringApplication(DataSourceApplication.class);
        ConfigurableApplicationContext context = application.run(args);
        //UserPoJpaRepository userPoJpaRepository = context.getBean(UserPoJpaRepository.class);
	}

}
