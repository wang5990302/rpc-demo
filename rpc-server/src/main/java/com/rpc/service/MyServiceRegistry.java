package com.rpc.service;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangzhijun on 2018/12/30.
 */
public class MyServiceRegistry {

    private static final Map<String, Object> registeredServices = new HashMap<String, Object>();


    public MyServiceRegistry(){

    }


    /**
     * 将服务配置
     * @param interfaceClass
     * @param implClass
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static void registerService(Class<?> interfaceClass, Class<?> implClass) {
        try {
            registeredServices.put(interfaceClass.getName(),implClass.newInstance());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /** 根据接口名 获取具体实现类
     * @param name
     * @param <T>
     * @return
     */
    public static <T>T getService(String name){
        return (T)registeredServices.get(name);
    }


}
