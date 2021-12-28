package com.wangjx.xrpc.core.namespace;

import java.lang.reflect.Method;

/**
 * @ClassName: NameSpace
 * @Description: 调度命名空间
 * @Author: wangjiaxing
 * @Date: 2021/12/27 16:01
 * @Version 1.0
 */
public class NameSpace {
    /**
     * 业务名称
     */
    private String serviceName;
    /**
     * 方法名称
     */
    private String methodName;
    /**
     * 调用方法实例
     */
    private Object target;
    /**
     * 调用方法
     */
    private Method method;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "NameSpace{" +
                "serviceName='" + serviceName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", target=" + target +
                ", method=" + method +
                '}';
    }
}
