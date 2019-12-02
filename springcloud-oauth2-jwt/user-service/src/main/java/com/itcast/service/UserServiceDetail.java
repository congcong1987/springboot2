package com.itcast.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.itcast.client.AuthServiceClient;
import com.itcast.dao.UserDao;
import com.itcast.dto.UserLoginDTO;
import com.itcast.entity.JWT;
import com.itcast.entity.User;
import com.itcast.exception.UserLoginException;
import com.itcast.utils.BPwdEncoderUtil;


/**
 * Created by zhangyong on 2017/5/10.
 */
@Service
public class UserServiceDetail implements UserDetailsService {

    @Autowired
    private UserDao userRepository;
    @Autowired
    AuthServiceClient client;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public User insertUser(String username,String  password){
        User user=new User();
        user.setUsername(username);
        user.setPassword(BPwdEncoderUtil.BCryptPassword(password));
        return userRepository.save(user);
    }

    public UserLoginDTO login(String username,String password){
        User user=userRepository.findByUsername(username);
        if (null == user) {
          throw new UserLoginException("error username");
        }
        if(!BPwdEncoderUtil.matches(password,user.getPassword())){
            throw new UserLoginException("error password");
        }
        // 获取token
        JWT jwt=client.getToken("Basic dXNlci1zZXJ2aWNlOjEyMzQ1Ng==","password",username,password);
        // 获得用户菜单
        if(jwt==null){
            throw new UserLoginException("error internal");
        }
        UserLoginDTO userLoginDTO=new UserLoginDTO();
        userLoginDTO.setJwt(jwt);
        userLoginDTO.setUser(user);
        return userLoginDTO;

    }
}
