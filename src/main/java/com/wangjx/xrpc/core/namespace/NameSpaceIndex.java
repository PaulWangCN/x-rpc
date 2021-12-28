package com.wangjx.xrpc.core.namespace;

import com.wangjx.xrpc.utils.ScanJarUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: NameSpaceIndex
 * @Description: 调度命名空间索引
 * @Author: wangjiaxing
 * @Date: 2021/12/27 16:36
 * @Version 1.0
 */
public class NameSpaceIndex {

    public static Map<String, NameSpace> rpcMethod = new HashMap<>();

    static {
        try {
            ScanJarUtils handlerScanner = new ScanJarUtils("com.wangjx.xrpc", true, null, null);
            Set<Class<?>> classes = handlerScanner.doScanAllClasses();
            for (Class clazz : classes) {
                System.out.println(clazz.getName());
                Annotation xService = clazz.getAnnotation(XService.class);
                if (xService != null) {
                    XService xs = (XService) xService;
                    System.out.println(clazz.getName() + "准备注册RPC调用服务");
                    Method[] declaredMethods = clazz.getDeclaredMethods();
                    Object target = clazz.newInstance();
                    for (Method method : declaredMethods) {
                        XMethod xMethod = method.getAnnotation(XMethod.class);
                        if (xMethod != null) {
                            System.out.println(xMethod.toString() + "准备注册RPC调用方法");
                            NameSpace nameSpace = new NameSpace();
                            nameSpace.setServiceName(xs.name());
                            nameSpace.setMethodName(xMethod.name());
                            nameSpace.setTarget(target);
                            nameSpace.setMethod(method);
                            rpcMethod.put(nameSpace.getServiceName() + "." + nameSpace.getMethodName(), nameSpace);
                        }
                    }
                }
            }
            System.out.println(rpcMethod);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
