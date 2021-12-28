package com.wangjx.xrpc.core.client;

import com.alibaba.fastjson.JSONObject;
import com.wangjx.xrpc.core.common.XRpcData;
import com.wangjx.xrpc.core.common.XRpcProtocol;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

//用于读取客户端发来的信息
@ChannelHandler.Sharable
public class XRpcClientHandler extends ChannelInboundHandlerAdapter {

    public BlockingQueue<XRpcData> queue = new LinkedBlockingQueue<>();

    // 客户端与服务端，连接成功的时候
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    // 只是读数据，没有写数据的话
    // 需要自己手动的释放的消息
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            // 用于获取客户端发来的数据信息
            XRpcProtocol body = (XRpcProtocol) msg;
            System.out.println("Client接受的客户端的信息 :" + body.toString());
            queue.add(JSONObject.parseObject(new String(body.getContent()), XRpcData.class));
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

}
