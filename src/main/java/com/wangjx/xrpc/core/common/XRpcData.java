package com.wangjx.xrpc.core.common;

import java.util.Arrays;

/**
 * @ClassName: XRpcData
 * @Description: 自定义数据格式
 * @Author: wangjiaxing
 * @Date: 2021/04/21 17:06
 * @Version 1.0
 */
public class XRpcData {
    /**
     * 调用服务
     */
    private String service;
    /**
     * 方法参数
     */
    private Object[] args;
    /**
     * 调用结果
     */
    private Object result;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "XRpcData{" +
                "service='" + service + '\'' +
                ", args=" + Arrays.toString(args) +
                ", result=" + result +
                '}';
    }
}
