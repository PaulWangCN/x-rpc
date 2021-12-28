package com.wangjx.xrpc.core.client;

import com.wangjx.xrpc.core.common.XRpcDecoder;
import com.wangjx.xrpc.core.common.XRpcEncoder;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: NettyClientInitializer
 * @Description: TODO
 * @Author: wangjiaxing
 * @Date: 2021/12/27 14:23
 * @Version 1.0
 */
public class XRpcClientInitializer extends ChannelInitializer<SocketChannel> {

    private ChannelInboundHandlerAdapter handlerAdapter;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //发送心跳
        ch.pipeline().addLast(new IdleStateHandler(0, 4, 0, TimeUnit.SECONDS));
        // 添加自定义协议的编解码工具
        ch.pipeline().addLast(new XRpcEncoder());
        ch.pipeline().addLast(new XRpcDecoder());
        // 处理网络IO
        ch.pipeline().addLast(handlerAdapter);
    }

    public XRpcClientInitializer(ChannelInboundHandlerAdapter handlerAdapter) {
        this.handlerAdapter = handlerAdapter;
    }

}
