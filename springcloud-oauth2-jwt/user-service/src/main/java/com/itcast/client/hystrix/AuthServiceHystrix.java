package com.itcast.client.hystrix;


import org.springframework.stereotype.Component;

import com.itcast.client.AuthServiceClient;
import com.itcast.entity.JWT;

/**
 * Created by zhangyong on 2017/5/31.
 */
@Component
public class AuthServiceHystrix implements AuthServiceClient {
    @Override
    public JWT getToken(String authorization, String type, String username, String password) {
    	System.out.println("熔断了**********");
        return null;
    }
}
