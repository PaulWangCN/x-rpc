package com.wangjx.xrpc.core.server;

import com.alibaba.fastjson.JSONObject;
import com.wangjx.xrpc.core.common.XRpcData;
import com.wangjx.xrpc.core.common.XRpcProtocol;
import com.wangjx.xrpc.core.namespace.XRpcDataProcessor;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class XRpcServerHandler extends ChannelInboundHandlerAdapter {

    private static BlockingQueue<XRpcData> datas = new LinkedBlockingQueue<>();

    // 用于获取客户端发送的信息
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            // 用于获取客户端发来的数据信息
            XRpcProtocol body = (XRpcProtocol) msg;
            XRpcData data = JSONObject.parseObject(new String(body.getContent()), XRpcData.class);
            System.out.println("server 收到信息: " + data);
            XRpcData XRpcData = XRpcDataProcessor.handle(data);
            // 会写数据给客户端
            XRpcProtocol response = new XRpcProtocol(XRpcData);
            // 当服务端完成写操作后，关闭与客户端的连接
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);

            // 当有写操作时，不需要手动释放msg的引用
            // 当只有读操作时，才需要手动释放msg的引用
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress().toString() + "已断开连接");
        super.channelInactive(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress().toString() + "已连接");
        super.channelActive(ctx);
    }
}
