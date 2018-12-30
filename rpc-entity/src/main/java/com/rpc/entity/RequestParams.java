package com.rpc.entity;

import java.io.Serializable;
import java.util.Arrays;

/**
 * 请求参数的封装
 * Created by wangzhijun on 2018/12/30.
 */
public class RequestParams implements Serializable{

    //类名
    private String interfaceName;

    //方法名
    private String methodName;

    //参数名
    private Object[] parameters;

    //参数类型
    private Class<?>[] parameterTypes;


    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    @Override
    public String toString() {
        return "RequestParams{" +
                "interfaceName='" + interfaceName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", parameters=" + Arrays.toString(parameters) +
                ", parameterTypes=" + parameterTypes +
                '}';
    }
}
