package com.rpc.client;

import com.rpc.entity.RequestParams;
import com.rpc.entity.RpcResponse;
import com.rpc.service.HelloService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by wangzhijun on 2018/12/30.
 */
public class RpcProxyFactory<T> implements InvocationHandler {

    private Class<T> tClass;


    public RpcProxyFactory(Class<T> tClass){
        this.tClass=tClass;
    }

    public T getProxyObject(){
        return (T)Proxy.newProxyInstance(tClass.getClassLoader(),new Class[]{tClass},this);
    }


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 处理Object中的方法
        if (Object.class == method.getDeclaringClass()) {
            String name = method.getName();
            if ("equals".equals(name)) {
                return proxy == args[0];
            } else if ("hashCode".equals(name)) {
                return System.identityHashCode(proxy);
            } else if ("toString".equals(name)) {
                return proxy.getClass().getName() + "@" +
                        Integer.toHexString(System.identityHashCode(proxy)) +
                        ", with InvocationHandler " + this;
            } else {
                throw new IllegalStateException(String.valueOf(method));
            }
        }

        //封装请求参数
        RequestParams requestParams=new RequestParams();
        requestParams.setInterfaceName(tClass.getName());
        requestParams.setMethodName(method.getName());
        requestParams.setParameters(args);
        requestParams.setParameterTypes(method.getParameterTypes());
        try {
            RpcClient rpcClient=new BioRpcClient("127.0.0.1",9090);
            RpcResponse rpcResponse = rpcClient.sendRequest(requestParams);

            if(rpcResponse.getStatus()==1){
                return rpcResponse.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        System.out.println(HelloService.class.getName());
    }
}
