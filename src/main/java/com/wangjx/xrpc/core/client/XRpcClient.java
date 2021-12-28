package com.wangjx.xrpc.core.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;


/**
 * @ClassName: PackNettyClient
 * @Description: 自定义协议netty客户端
 * @Author: wangjiaxing
 * @Date: 2021/04/19 17:35
 * @Version 1.0
 */
public class XRpcClient {
    public ChannelFuture channelFuture;
    // 配置客户端NIO线程组
    EventLoopGroup group = new NioEventLoopGroup();
    // 客户端辅助启动类 对客户端配置
    Bootstrap b = new Bootstrap();
    /**
     * 连接服务器
     *
     * @param port
     * @param host
     * @throws Exception
     */
    public void connect(int port, String host, ChannelInboundHandlerAdapter handlerAdapter) throws Exception {
        try {
            b.group(group)//
                    .channel(NioSocketChannel.class)//
                    .option(ChannelOption.TCP_NODELAY, true)//
                    .handler(new XRpcClientInitializer(handlerAdapter));//
            // 异步链接服务器 同步等待链接成功
            channelFuture = b.connect(host, port);
            // 等待链接关闭
            channelFuture.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
            System.out.println("客户端优雅的释放了线程资源...");
        }

    }

}
