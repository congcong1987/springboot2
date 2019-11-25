package com.itcast.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcast.bean.Menu;
import com.itcast.mapper.MenuMapper;

@Service
public class MenuService {
	 @Autowired
	    MenuMapper menuMapper;
	    public List<Menu> getAllMenus() {
	        return menuMapper.getAllMenus();
	    }
}
