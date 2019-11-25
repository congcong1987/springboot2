package com.itcast.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.itcast.bean.Person;

@Mapper
public interface PersonMapper {

	
	/*@Results (
			{ @Result (property = "id", column = "id"),
			@Result (
				property = "name",
						column = "n"
			),
			@Result (
				property = "address",
						column = "a"
			) }
		)*/
	
	
	
		@Select("select * from person where id=#{id}")
	     Person getPersonById(Long id);
	
		@Select("select * from person where name  like  concat('%',#{name},'%') ")
		List<Person> queryByName(String name);
		
		  @Delete("delete from person where id=#{id}")
		    void deletePersonById(Long id);
		  
		  @Insert({ "insert into user(username,address) values(#{username},#{address})" }) 
		  @SelectKey (statement = "select last_insert_id()",keyProperty = "id",before = false,resultType = Integer .class) 
		  Integer addUser (Person person);
		  
		  @Update("update person set age=#{age} where id=#{id}")
		  void updateInfo(@Param("age") Integer age,@Param("id") Long id);
}
