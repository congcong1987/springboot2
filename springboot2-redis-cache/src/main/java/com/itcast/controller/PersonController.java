package com.itcast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.itcast.bean.Person;
import com.itcast.service.PersonService;

@RestController
public class PersonController {

	 @Autowired
	     StringRedisTemplate stringRedisTemplate; //操作 k-v 字符串
	 
	      @Autowired
	  RedisTemplate redisTemplate;  //k- v 都是对象
	@Autowired
	PersonService personService;
	

	@GetMapping("/person/{id}")
	public Person queryById(@PathVariable("id") Long id){
		 Person person = personService.queryById(id);
		 redisTemplate.opsForValue().set("zy2", person);
		return person;
		
	}
	
	
	@GetMapping("/person/del/{id}")
	public String delById(@PathVariable("id")Long id){
		personService.del(id);
		return "del success";
	}
	
	@GetMapping("/person/update/{id}")
	public String updateAge(@PathVariable("id")Long id){
		personService.updateAge(23, id);
		return "update sucess====>"+id;
	}
	
	
}
