package com.shine.netty.boundtest;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

public class DemoTest {
    @Test
    public void test() {
        final InHandlerDemo handler = new InHandlerDemo();

        ChannelInitializer<EmbeddedChannel> initializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel embeddedChannel) throws Exception {
                embeddedChannel.pipeline().addLast(handler);
            }
        };

        EmbeddedChannel channel = new EmbeddedChannel(initializer);
        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(1);

        channel.writeInbound(buf);
        channel.flush();

        channel.writeInbound(buf);
        channel.flush();

        channel.close();
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
