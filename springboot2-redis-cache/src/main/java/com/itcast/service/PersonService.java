package com.itcast.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.itcast.bean.Person;
import com.itcast.mapper.PersonMapper;

@Service
@CacheConfig(cacheNames = "person")
public class PersonService {
	
	@Autowired
	PersonMapper personMapper;
	
	@Cacheable(key="targetClass+methodName+#id")
	public Person queryById(Long id){
		System.out.println("调用personid="+id);
		return personMapper.getPersonById(id);
	}

	@CacheEvict(allEntries=true,beforeInvocation=true)
	public void del(Long id){
		personMapper.deletePersonById(id);
	}
	
	@CachePut(key = "targetClass+methodName+#id")
	public void updateAge(Integer age,Long id){
		personMapper.updateInfo(age, id);
	}
}
