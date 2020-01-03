package com.shine.netty.ehco;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 *  角色：Leader
 *  动作：通过心跳信号维护自己的Leader身份
 */
public class HeartBeatTimerHandler extends ChannelInboundHandlerAdapter {

    private static final int HEARTBEAT_DELAY = 5;
    private static final int HEARTBEAT_PERIOD = 10;
    private static final byte[] bytes = "123".getBytes(Charset.forName("utf-8"));

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("心跳开始");
        scheduleSendHeartBeat(ctx);
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("心跳开始");
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("心跳开始");
        super.channelReadComplete(ctx);
    }

    private void scheduleSendHeartBeat(ChannelHandlerContext ctx) {
        ctx.executor().scheduleAtFixedRate(() -> {
            if (ctx.channel().isActive()) { //TODO： 修改状态判断
                ByteBuf buf = ctx.channel().alloc().buffer();
                buf.writeBytes(bytes);
                ctx.writeAndFlush(buf);
                scheduleSendHeartBeat(ctx);
            }
        }, HEARTBEAT_DELAY, HEARTBEAT_PERIOD, TimeUnit.SECONDS);
    }
}
