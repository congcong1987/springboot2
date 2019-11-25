package com.itcast.mapper;

import java.util.List;

import com.itcast.bean.Role;
import com.itcast.bean.User;

/**
 * @Author 江南一点雨
 * @Site www.javaboy.org 2019-08-11 17:12
 */
public interface UserMapper {

    List<Role> getRolesById(Integer id);

    User loadUserByUsername(String username);
}
