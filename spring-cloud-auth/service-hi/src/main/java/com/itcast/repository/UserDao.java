package com.itcast.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itcast.domain.User;

public interface UserDao extends JpaRepository<User, Long> {

	User findByUsername(String username);
}
