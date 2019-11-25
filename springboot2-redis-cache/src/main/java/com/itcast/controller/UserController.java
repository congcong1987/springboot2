package com.itcast.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itcast.bean.User;
import com.itcast.service.UserService;

@RestController
public class UserController {

	
	@Autowired
	UserService userService;
	
	
	@GetMapping("/api/userall")
	public List<User> queryAll(){
		return userService.getAllUser();
	}
}
