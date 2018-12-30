package com.rpc.service;

import com.rpc.service.impl.HelloServiceImpl;

import javax.imageio.spi.ServiceRegistry;


/**
 * Created by wangzhijun on 2018/12/30.
 */
public class ServerApplication {

    public static void main(String[] args) {
        //注册服务
        MyServiceRegistry.registerService(HelloService.class, HelloServiceImpl.class);
        //启动服务
        new  BioRpcServer(9090).start();

    }
}
