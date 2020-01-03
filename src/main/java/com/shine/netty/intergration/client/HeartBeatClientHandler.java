package com.shine.netty.intergration.client;

import com.shine.netty.intergration.commend.HeartBeatCmd;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

public class HeartBeatClientHandler extends ChannelInboundHandlerAdapter {

    final int port;

    public HeartBeatClientHandler(int port) {
        this.port = port;
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        ctx.executor().scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                if (ctx.channel().isActive()){
                    System.out.println("周期发送心跳信号");
                    ctx.writeAndFlush(HeartBeatCmd.HEART);
                }
            }
        }, 0, 3, TimeUnit.SECONDS);
        super.handlerAdded(ctx);
    }
}
