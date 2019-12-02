package com.itcast.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.itcast.entity.User;
/**
 * Created by fangzhipeng on 2017/5/27.
 */

public interface UserDao extends JpaRepository<User, Long> {

	User findByUsername(String username);
}
