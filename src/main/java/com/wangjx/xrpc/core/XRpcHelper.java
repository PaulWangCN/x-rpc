package com.wangjx.xrpc.core;

import com.wangjx.xrpc.core.client.XRpcClient;
import com.wangjx.xrpc.core.client.XRpcClientHandler;
import com.wangjx.xrpc.core.common.XRpcData;
import com.wangjx.xrpc.core.common.XRpcProtocol;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: XRpcHelper
 * @Description: TODO
 * @Author: wangjiaxing
 * @Date: 2021/12/27 16:58
 * @Version 1.0
 */
public class XRpcHelper {

    public static Object invoke(XRpcData XRpcData) throws Exception {
        XRpcClient xRpcClient = new XRpcClient();
        XRpcClientHandler handler = new XRpcClientHandler();
        Runnable runnable = () -> {
            try {
                xRpcClient.connect(9999, "127.0.0.1", handler);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        int count = 0;
        //很重要的判断
        while (xRpcClient.channelFuture == null || !xRpcClient.channelFuture.isDone()) {
            count++;
            TimeUnit.MILLISECONDS.sleep(1);
        }
        System.out.println("循环等待：" + count);

        XRpcProtocol XRpcProtocol = new XRpcProtocol(XRpcData);
        xRpcClient.channelFuture.channel().writeAndFlush(XRpcProtocol);
        return handler.queue.take().getResult();
    }

}
