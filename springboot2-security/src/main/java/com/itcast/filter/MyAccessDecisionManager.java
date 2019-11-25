package com.itcast.filter;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
/**
 * 自定义一个决策管理器MyAccessDecisionManager实现AccessDecisionManager接口。
 * 其中的decide方法，决定某一个用户是否有权限访问某个url
 * @author cong
 *
 */
@Component
public class MyAccessDecisionManager implements AccessDecisionManager{

	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> collection)
			throws AccessDeniedException, InsufficientAuthenticationException {
		
		   System.out.println(object.toString()); // object is a URL
		
		 for (ConfigAttribute attribute : collection) {
	            if ("ROLE_login".equals(attribute.getAttribute())) {
	                if (authentication instanceof AnonymousAuthenticationToken) {
	                    throw new AccessDeniedException("非法请求!");
	                } else {
	                    return;
	                }
	            }
	            
	          //访问所请求资源所需要的权限
	            //访问所请求资源所需要的权限
	            String needPermission = attribute.getAttribute();
	            System.out.println("访问"+object.toString()+"需要的权限是：" + needPermission);
	            
	            
	            //用户所拥有的权限authentication
	            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	            for (GrantedAuthority authority : authorities) {
	                if (authority.getAuthority().equals(attribute.getAttribute())) {
	                    return;
	                }
	            }
	        }
	        throw new AccessDeniedException("非法请求!");
		
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

}
