package com.shine.netty.ehco;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.InetSocketAddress;

// 表示一个Hander可以被多个Channel共享
@Sharable
// 不同类型的时间使用不同的ChannelHandler
// ChannelInboundHandlerAdapter用来处理数据传入事件
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    // 消息传入的时候触发
    public void channelRead(ChannelHandlerContext context, Object msg) throws Exception {
        InetSocketAddress ipSocket = (InetSocketAddress) context.channel().remoteAddress();
        String address = ipSocket.getAddress().getHostAddress();
        int port = ipSocket.getPort();
        System.out.print(address + ":" + port + ": ");
        ByteBuf buf = (ByteBuf) msg;
        if (!buf.hasArray()) {
            int length = buf.readableBytes();
            byte[] array = new byte[length]; // 将数据读取到堆内存中
            buf.getBytes(buf.readerIndex(), array);
            System.out.println(new String(array, "UTF-8"));
        }
        ChannelFuture cf = context.writeAndFlush(msg); // 回传消息
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ChannelSet.SET.add(ctx.channel());
        System.out.println(1234);
        super.channelActive(ctx);
    }
}
