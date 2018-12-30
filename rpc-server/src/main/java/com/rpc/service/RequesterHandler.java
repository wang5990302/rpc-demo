package com.rpc.service;

import com.rpc.entity.RequestParams;
import com.rpc.entity.RpcResponse;

import java.lang.reflect.Method;

/**
 * Created by wangzhijun on 2018/12/30.
 */
public class RequesterHandler {

    public static RpcResponse handler(RequestParams requestParams){
        try {

            //获取服务
            Object service = MyServiceRegistry.getService(requestParams.getInterfaceName());
            if(service!=null){
                Class<?> aClass = service.getClass();
                Method method=aClass.getMethod(requestParams.getMethodName(),requestParams.getParameterTypes());
                Object invoke = method.invoke(service, requestParams.getParameters());
                return RpcResponse.ok(invoke);
            }else {
                return RpcResponse.error("未知服务！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
}
