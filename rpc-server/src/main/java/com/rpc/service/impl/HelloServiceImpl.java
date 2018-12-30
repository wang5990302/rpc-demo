package com.rpc.service.impl;

import com.rpc.service.HelloService;

/**
 * Created by wangzhijun on 2018/12/30.
 */
public class HelloServiceImpl implements HelloService{


    @Override
    public String say(String message) {

        return "hello"+message;
    }
}
