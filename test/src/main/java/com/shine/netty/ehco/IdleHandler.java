package com.shine.netty.ehco;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * 角色：Follower
 * 动作：如果没有接受到Leader的心跳就进行投票操作
 */
public class IdleHandler extends IdleStateHandler {
    private static final int READER_IDLE_TIME = 5;

    public IdleHandler() {
        super(READER_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
    }

    // TODO：切换状态到Candidate
    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) {
        System.out.println(READER_IDLE_TIME + "秒内未读到数据，关闭连接");
//        ctx.channel().close();
    }
}
