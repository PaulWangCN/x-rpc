package com.wangjx.xrpc;

import com.wangjx.xrpc.core.XRpcHelper;
import com.wangjx.xrpc.core.common.XRpcData;


/**
 * @ClassName: RpcMain
 * @Description:
 * @Author: wangjiaxing
 * @Date: 2021/12/27 15:51
 * @Version 1.0
 */
public class RpcMain {

    public static void main(String[] args) throws Exception {
        XRpcData xRpcData = new XRpcData();
        xRpcData.setService("test.check");
        xRpcData.setArgs(null);
        System.out.println("RPC调用结果：" + XRpcHelper.invoke(xRpcData));
        long a = System.currentTimeMillis();
        for (int i = 0; i< 10; i++) {
            xRpcData = new XRpcData();
            xRpcData.setService("test.needArgs");
            xRpcData.setArgs(new Object[]{"2", 1});
            Object invoke = XRpcHelper.invoke(xRpcData);
            System.out.println("RPC调用结果：" + invoke);
        }
        System.out.println("调用10次耗时: " + (System.currentTimeMillis() - a) + "ms");
    }

}
