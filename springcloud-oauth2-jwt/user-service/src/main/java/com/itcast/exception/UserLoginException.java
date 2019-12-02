package com.itcast.exception;

/**
 * Created by zhangyong on 2017/6/1.
 */
public class UserLoginException extends RuntimeException{

    public UserLoginException(String message) {
        super(message);
    }

}
