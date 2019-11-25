package com.itcast.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;


@Configuration
@EnableAuthorizationServer
@Order(6)
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{
	
	
	 @Autowired
	    @Qualifier("authenticationManagerBean")
	    private AuthenticationManager authenticationManager;
	 
	 	@Resource(name = "dataSource")
	    private DataSource dataSource;
	    
	    @Autowired
	    private UserDetailsService userDetailsService;
	    
	    @Autowired
	    RedisConnectionFactory redisConnectionFactory;
	 
	 
	    @Bean
	    public TokenStore tokenStore() {
//	        return new InMemoryTokenStore(); //使用内存中的 token store
	        return new JdbcTokenStore(dataSource); ///使用Jdbctoken store
	    }

	    @Override
	    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	    	clients.jdbc(dataSource);
	      /*  clients.jdbc(dataSource)
	                .withClient("client")//client_id
	                .secret(new BCryptPasswordEncoder().encode("123456"))
	                .authorizedGrantTypes("password", "refresh_token")//允许授权范围
	                .authorities("ROLE_ADMIN","ROLE_USER")//客户端可以使用的权限
	              //  .scopes( "all")
	                .scopes( "read", "write")
	                .accessTokenValiditySeconds(7200)
	                .refreshTokenValiditySeconds(7200);*/
	    }
	 
	    @Override
	    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	        endpoints.tokenStore(new RedisTokenStore(redisConnectionFactory))
	                .authenticationManager(authenticationManager)
	                .userDetailsService(userDetailsService);//必须设置 UserDetailsService 否则刷新token 时会报错
	    }
	 
	    @Override
	    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
	        security
	                .tokenKeyAccess("permitAll()")
	                .checkTokenAccess("isAuthenticated()")
	                .allowFormAuthenticationForClients();//允许表单登录
	 
	    }

	
	

}
