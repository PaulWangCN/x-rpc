package com.wangjx.xrpc.test;

import com.wangjx.xrpc.core.namespace.XMethod;
import com.wangjx.xrpc.core.namespace.XService;

/**
 * @ClassName: TestMethod
 * @Description: TODO
 * @Author: wangjiaxing
 * @Date: 2021/12/27 16:07
 * @Version 1.0
 */
@XService(name = "test")
public class TestMethod {

    @XMethod(name="check")
    public String check() {
        return "test.check invoke success!";
    }

    @XMethod(name="needArgs")
    public int needArgs(String a, Integer b) {
        return Integer.parseInt(a) + b;
    }

}
