package com.wangjx.xrpc.core.namespace;

import com.wangjx.xrpc.core.common.XRpcData;

/**
 * @ClassName: TestDataHandler
 * @Description: TODO
 * @Author: wangjiaxing
 * @Date: 2021/12/27 15:32
 * @Version 1.0
 */
public class XRpcDataProcessor {

    public static XRpcData handle(XRpcData data) {
        try {
            NameSpace nameSpace = NameSpaceIndex.rpcMethod.get(data.getService());
            Object invokeResult = nameSpace.getMethod().invoke(nameSpace.getTarget(), data.getArgs());
            System.out.println(invokeResult);
            XRpcData result = new XRpcData();
            result.setResult(invokeResult);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            XRpcData result = new XRpcData();
            result.setResult("服务调用失败");
            return result;
        }
    }
}
