package com.itcast.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itcast.filter.VerifyCodeFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	VerifyCodeFilter verifyCodeFilter;
	
	
	 @Bean
	    PasswordEncoder passwordEncoder() {
	        return NoOpPasswordEncoder.getInstance();
	    }

	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    	
	    	
	    	
	        auth.inMemoryAuthentication()
	                .withUser("javaboy").password("123").roles("admin")
	                .and()
	                .withUser("江南一点雨").password("456").roles("user");
	    }
	    
	    
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	    	http.addFilterBefore (verifyCodeFilter,UsernamePasswordAuthenticationFilter.class);
	    	
	        http.authorizeRequests()
	                .antMatchers("/admin/**").hasRole("admin")
//	                .antMatchers("user/**").hasAnyRole("admin", "user")
	                .antMatchers("/user/**").access("hasAnyRole('user','admin')")
	                .anyRequest().authenticated()
	                .and()
	                .formLogin()
	                .loginProcessingUrl("/doLogin")
	                .loginPage("/login")
	                .usernameParameter("uname")
	                .passwordParameter("passwd")
	                .successHandler(new AuthenticationSuccessHandler() {
	                    @Override
	                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
	                        resp.setContentType("application/json;charset=utf-8");
	                        PrintWriter out = resp.getWriter();
	                        Map<String, Object> map = new HashMap<>();
	                        map.put("status", 200);
	                        map.put("msg", authentication.getPrincipal());
	                        out.write(new ObjectMapper().writeValueAsString(map));
	                        out.flush();
	                        out.close();
	                    }
	                })
	                .failureHandler(new AuthenticationFailureHandler() {
	                    @Override
	                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
	                        resp.setContentType("application/json;charset=utf-8");
	                        PrintWriter out = resp.getWriter();
	                        Map<String, Object> map = new HashMap<>();
	                        map.put("status", 401);
	                        if (e instanceof LockedException) {
	                            map.put("msg", "账户被锁定，登录失败!");
	                        } else if (e instanceof BadCredentialsException) {
	                            map.put("msg", "用户名或密码输入错误，登录失败!");
	                        } else if (e instanceof DisabledException) {
	                            map.put("msg", "账户被禁用，登录失败!");
	                        } else if (e instanceof AccountExpiredException) {
	                            map.put("msg", "账户过期，登录失败!");
	                        } else if (e instanceof CredentialsExpiredException) {
	                            map.put("msg", "密码过期，登录失败!");
	                        } else {
	                            map.put("msg", "登录失败!");
	                        }
	                        out.write(new ObjectMapper().writeValueAsString(map));
	                        out.flush();
	                        out.close();
	                    }
	                })
	                .permitAll()
	                .and()
	                .logout()
	                .logoutUrl("/logout")
	                .logoutSuccessHandler(new LogoutSuccessHandler() {
	                    @Override
	                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
	                        resp.setContentType("application/json;charset=utf-8");
	                        PrintWriter out = resp.getWriter();
	                        Map<String, Object> map = new HashMap<>();
	                        map.put("status", 200);
	                        map.put("msg", "注销登录成功!");
	                        out.write(new ObjectMapper().writeValueAsString(map));
	                        out.flush();
	                        out.close();
	                    }
	                })
	                .and()
	                .csrf().disable();
	    }
	    
	    /**
	     * 忽略的url
	     */
	    @Override
	    public void configure(WebSecurity web) throws Exception {
	        web.ignoring().antMatchers("/error", "/swagger-ui.html", 
					"/back/admin/getCountInfo", "/swagger-resources/**", "/v2/api-docs","/vercode","/index.html",
					"/api/**", "/pub/**");//pub：用于测试，swagger测试用？
	    }
	
}
