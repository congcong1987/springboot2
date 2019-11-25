package com.itcast.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itcast.bean.User;
import com.itcast.mapper.UserMapper;

@Service
public class UserService {
	
	@Autowired
	UserMapper mapper;
	
	public List<User> getAllUser(){
		return mapper.getAllUser();
	}

}
