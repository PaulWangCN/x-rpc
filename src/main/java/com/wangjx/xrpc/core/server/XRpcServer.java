package com.wangjx.xrpc.core.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @ClassName: XRpcServer
 * @Description: TODO
 * @Author: wangjiaxing
 * @Date: 2021/12/27 14:08
 * @Version 1.0
 */
public class XRpcServer {

    public void start() {
        // 配置NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 服务器辅助启动类配置
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new XRpcServerInitializer())//
                    .option(ChannelOption.SO_BACKLOG, 1024) // 设置tcp缓冲区 // (5)
                    .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)
            // 绑定端口 同步等待绑定成功
            int port = 9999;
            ChannelFuture f = b.bind(port).sync(); // (7)
            // 等到服务端监听端口关闭
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 优雅释放线程资源
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }


    public static void main(String[] args) {
        XRpcServer server = new XRpcServer();
        server.start();
    }
}
