package com.itcast.filter;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.itcast.bean.Menu;
import com.itcast.bean.Role;
import com.itcast.service.MenuService;

@Component
public class MyFilter implements FilterInvocationSecurityMetadataSource {

	 AntPathMatcher pathMatcher = new AntPathMatcher(); //spring 匹配路径规则
	    @Autowired
	    MenuService menuService;
	
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
		 String requestUrl = ((FilterInvocation) o).getRequestUrl();
	        List<Menu> allMenus = menuService.getAllMenus();
	        for (Menu menu : allMenus) {
	            if (pathMatcher.match(menu.getPattern(), requestUrl)) {
	                List<Role> roles = menu.getRoles();
	                String[] rolesStr = new String[roles.size()];
	                for (int i = 0; i < roles.size(); i++) {
	                    rolesStr[i] = roles.get(i).getName();
	                }
	                return SecurityConfig.createList(rolesStr);
	            }
	        }
	        return SecurityConfig.createList("ROLE_login");
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
