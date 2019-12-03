package com.shine.netty.boundtest;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

public class OutBoundTest {
    public class OutHandlerA extends ChannelOutboundHandlerAdapter {
        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            System.out.println("A");
            super.write(ctx, msg, promise);
        }
    }

    public class OutHandlerB extends ChannelOutboundHandlerAdapter {
        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            System.out.println("B");
            super.write(ctx, msg, promise);
        }
    }

    public class OutHandlerC extends ChannelOutboundHandlerAdapter {
        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            System.out.println("C");
            super.write(ctx, msg, promise);
        }
    }

    @Test
    public void test(){
        ChannelInitializer<EmbeddedChannel> initializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel embeddedChannel) throws Exception {
                embeddedChannel.pipeline().addLast(new InBoundTest.HandlerA());
//                embeddedChannel.pipeline().addLast(new InBoundTest.HandlerB());
//                embeddedChannel.pipeline().addLast(new InBoundTest.HandlerC());
                embeddedChannel.pipeline().addLast(new OutHandlerA());
//                embeddedChannel.pipeline().addLast(new OutHandlerB());
//                embeddedChannel.pipeline().addLast(new OutHandlerC());
            }
        };
        EmbeddedChannel channel = new EmbeddedChannel(initializer);

        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(1);
        channel.writeOutbound(buf);
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
