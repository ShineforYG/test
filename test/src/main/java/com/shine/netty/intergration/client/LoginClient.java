package com.shine.netty.intergration.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import com.shine.netty.intergration.commend.LoginCmd;

import java.io.UnsupportedEncodingException;

/**
 * 客户端
 */
public class LoginClient {
    public static void main(String[] args) {
        LoginClient client = new LoginClient(9091, "127.0.0.1", 12345);
        client.start();
    }

    private int listenPort;
    private int port;
    private String addv;

    Bootstrap bootstrap = new Bootstrap();

    public LoginClient(int port, String addv, int listenPort) {
        this.port = port;
        this.addv = addv;
        this.listenPort = listenPort;
    }

    public void start() {
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            bootstrap.group(worker);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.remoteAddress(addv, port);
            bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel channel) throws Exception {
                    channel.pipeline().addLast("login", new LoginClientHandler(port));
                }
            });
            ChannelFuture channelFuture = bootstrap.connect();
            channelFuture.sync();
            Channel channel = channelFuture.channel();

            // 注册
            ByteBuf buf = channel.alloc().buffer();
            String loginMsg = LoginCmd.getLoginCmd(listenPort, false);
            byte[] bytes = loginMsg.getBytes("UTF-8");
            buf.writeBytes(bytes);
            channel.writeAndFlush(buf);
        } catch (InterruptedException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
        }
    }
}
