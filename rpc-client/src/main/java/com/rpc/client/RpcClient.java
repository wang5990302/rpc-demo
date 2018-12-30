package com.rpc.client;

import com.rpc.entity.RequestParams;
import com.rpc.entity.RpcResponse;

/**
 * Created by wangzhijun on 2018/12/30.
 */
public interface RpcClient {

    public RpcResponse sendRequest(RequestParams request) throws Exception;
}
