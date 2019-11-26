package com.itcast.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itcast.bean.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


/**
 * 
 * 无状态的
 * 登录验证
 * @author DELL
 *
 */
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

	

	 public JwtLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
	        super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
	        setAuthenticationManager(authenticationManager);
	    }

	 
	 //从登录请求中提取出参数，去校验用户名密码是否正确
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		 User user = new ObjectMapper().readValue(req.getInputStream(), User.class);
		 
		// 进行自动校验。
		   return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
	}

/**
 * 第二步如果校验成功，就会来到successfulAuthentication回调中，
 * 在successfulAuthentication方法中，将用户角色遍历然后用一个 , 连接起来，然后再利用Jwts去生成token，按照代码的顺序，
 * 生成过程一共配置了四个参数，分别是用户角色、主题、过期时间以及加密算法和密钥，然后将生成的token写出到客户端。
 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse resp, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		 Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
		 StringBuffer as = new StringBuffer();
	        for (GrantedAuthority authority : authorities) {
	            as.append(authority.getAuthority())
	                    .append(",");
	        }
	        String jwt = Jwts.builder()
	                .claim("authorities", as)//配置用户角色
	                .setSubject(authResult.getName())//// 主题
	                .setExpiration(new Date(System.currentTimeMillis() + 10 * 60 * 1000))// 过期时间
	                .signWith(SignatureAlgorithm.HS512,"sang@123")//// 签名算法以及密匙
	                .compact();
	        resp.setContentType("application/json;charset=utf-8");
	        PrintWriter out = resp.getWriter();
	        out.write(new ObjectMapper().writeValueAsString(jwt));
	        out.flush();
	        out.close();
	
	}

	
	

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse resp,
			AuthenticationException failed) throws IOException, ServletException {
		resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        Map<String,String> mapfail=new HashMap<String,String>();
        mapfail.put("stauts", "0");
        mapfail.put("message", "登录失败");
        out.write("{status:'0',message:'登录失败'}");
        System.out.println();
        out.flush();
        out.close();
	}
	
	

}
