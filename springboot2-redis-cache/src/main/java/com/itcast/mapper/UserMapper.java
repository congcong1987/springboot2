package com.itcast.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

import com.itcast.bean.User;

/**
 * @Author 江南一点雨
 * @Site www.javaboy.org 2019-07-28 9:28
 */
//@Mapper
public interface UserMapper {
	

	@Results (
			{ 
			@Result (
				property = "userName",
						column = "user_name"
			) }
		)
	
	
    List<User> getAllUser();
}
