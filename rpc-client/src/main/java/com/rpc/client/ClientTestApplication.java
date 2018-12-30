package com.rpc.client;

import com.rpc.service.HelloService;

/**
 * Created by wangzhijun on 2018/12/30.
 */
public class ClientTestApplication {

    public static void main(String[] args) {

        //通过代理工厂获取服务
        HelloService helloService=new RpcProxyFactory<HelloService>(HelloService.class).getProxyObject();
        String say = helloService.say("wzj");

        System.out.println(say);

    }
}
