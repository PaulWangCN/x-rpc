package com.wangjx.xrpc.core.common;


import com.alibaba.fastjson.JSONObject;

/**
 * <pre>
 * 自己定义的协议
 *  数据包格式
 * +——----—--—+——-----——+——----——+
 * |协议开始标志|  长度   |   数据  |
 * +——----—--—+——-----——+——----——+
 * 1.协议开始标志head_data，为int类型的数据，16进制表示为0X50A64D
 * 2.传输数据的长度contentLength，int类型
 * 3.要传输的数据
 * </pre>
 */
public class XRpcProtocol {
    /**
     * 消息的开头的信息标志
     */
    private final int headData = ConstantValue.HEAD_DATA;
    /**
     * 消息的长度
     */
    private int contentLength;
    /**
     * 消息的内容
     */
    private byte[] content;

    /**
     * 用于初始化，SmartCarProtocol
     *
     * @param contentLength
     *            协议里面，消息数据的长度
     * @param content
     *            协议里面，消息的数据
     */
    public XRpcProtocol(int contentLength, byte[] content) {
        this.contentLength = contentLength;
        this.content = content;
    }

    public XRpcProtocol(XRpcData data) {
        String msg = JSONObject.toJSONString(data);
        // 获得要发送信息的字节数组
        byte[] content = msg.getBytes();
        this.content = content;
        // 要发送信息的长度
        this.contentLength = content.length;
    }

    public int getHead_data() {
        return headData;
    }

    public int getContentLength() {
        return contentLength;
    }

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "StarProtocol [head_data=" + headData + ", contentLength="
                + contentLength + ", content raw data=" + new String(content) + "]";
    }

}
