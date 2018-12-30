package com.rpc.client;

import com.rpc.entity.RequestParams;
import com.rpc.entity.RpcResponse;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by wangzhijun on 2018/12/30.
 */
public class BioRpcClient implements RpcClient {

    //请求的主机地址
    private String host;

    //请求的端口
    private int port;

    public BioRpcClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public RpcResponse sendRequest(RequestParams request) throws Exception {
        try {
            Socket socket=new Socket(host,port);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            out.writeObject(request);
            Object o = in.readObject();
           return (RpcResponse)o;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
