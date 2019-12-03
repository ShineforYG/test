package com.shine.netty.ehco;

import io.netty.util.internal.ConcurrentSet;

import io.netty.channel.Channel;

public class ChannelSet {
    public static final ConcurrentSet<Channel> SET =new ConcurrentSet<>();
}
