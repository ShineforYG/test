package com.shine.netty.server;

import io.netty.channel.ChannelHandler;
import io.netty.handler.timeout.IdleStateHandler;

@ChannelHandler.Sharable
public class HeartHandler extends IdleStateHandler {
    public HeartHandler(int readerIdleTimeSeconds, int writerIdleTimeSeconds, int allIdleTimeSeconds) {
        super(readerIdleTimeSeconds, writerIdleTimeSeconds, allIdleTimeSeconds);
    }
}
