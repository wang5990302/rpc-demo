package com.rpc.service;

import com.rpc.entity.RequestParams;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangzhijun on 2018/12/30.
 */
public class BioRpcServer {

    private Integer port;//默认端口

    private volatile boolean shudown=false;//是否停止

    private static final ExecutorService es= Executors.newCachedThreadPool();

    public BioRpcServer(){

    }

    public  BioRpcServer(Integer port){

        this.port=port;
    }

    //启动服务
    public  void start(){
        try {
            //创建服务端socket连接
            ServerSocket serverSocket=new ServerSocket(this.port);
            while (!shudown){
                //接受客户端请求
                Socket accept = serverSocket.accept();
                es.execute(()->{
                    try {
                        //使用jdk序列化流
                        ObjectInputStream objectInputStream=new ObjectInputStream(accept.getInputStream());
                        ObjectOutputStream objectOutputStream=new ObjectOutputStream(accept.getOutputStream());
                        RequestParams requestParams = (RequestParams) objectInputStream.readObject();
                        //打印请求过来的参数
                        System.out.println(requestParams.toString());
                        //处理请求，将结果返回给
                        objectOutputStream.writeObject(RequesterHandler.handler(requestParams));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

        }catch (Exception e){

        }


    }

}
