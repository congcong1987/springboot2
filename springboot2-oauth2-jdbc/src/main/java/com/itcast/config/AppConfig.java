package com.itcast.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

/**
 * https://blog.csdn.net/peterwanghao/article/details/81221671
 * @author DELL
 *
 */
@Configuration
public class AppConfig {
	@Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Value("${spring.datasource.driver-class-name}")
    private String dbDriverClassName;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Bean
    public DataSource dataSource() {
    	return DruidDataSourceBuilder.create().build();
    }

   /* @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource());
    }*/

}
