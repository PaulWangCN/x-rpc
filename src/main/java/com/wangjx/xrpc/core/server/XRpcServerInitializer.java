package com.wangjx.xrpc.core.server;

import com.wangjx.xrpc.core.common.XRpcDecoder;
import com.wangjx.xrpc.core.common.XRpcEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: XRpcServerInitializer
 * @Description:
 * @Author: wangjiaxing
 * @Date: 2021/12/27 14:15
 * @Version 1.0
 */
public class XRpcServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new IdleStateHandler(0, 5, 0, TimeUnit.SECONDS));
        // 添加自定义协议的编解码工具
        ch.pipeline().addLast(new XRpcEncoder());
        ch.pipeline().addLast(new XRpcDecoder());
        // 处理网络IO
        ch.pipeline().addLast(new XRpcServerHandler());
    }

}
