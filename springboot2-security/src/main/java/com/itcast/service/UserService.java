package com.itcast.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.itcast.bean.User;
import com.itcast.mapper.UserMapper;

/**
 * @Author 江南一点雨
 * @Site www.javaboy.org 2019-08-11 17:13
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在!");
        }
        user.setRoles(userMapper.getRolesById(user.getId()));
        return user;
    }
}
