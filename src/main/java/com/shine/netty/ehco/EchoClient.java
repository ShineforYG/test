package com.shine.netty.ehco;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.UnsupportedEncodingException;


public class EchoClient {

    public static void main(String[] args) {
        EchoClient echoClient = new EchoClient(EchoConfig.SERVER_ADDR, EchoConfig.SERVER_PORT);
        echoClient.runServer();
    }

    private int serverPort;
    private String serverAddr;

    Bootstrap bootstrap = new Bootstrap();

    public EchoClient(String addr, int port) {
        this.serverAddr = addr;
        this.serverPort = port;
    }

    public void runServer() {
        EventLoopGroup worker = new NioEventLoopGroup();

        try {
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new EchoClientHandler());
                }
            });
            bootstrap.group(worker);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            final ChannelFuture future_1 = bootstrap.connect(serverAddr, serverPort);
            future_1.sync();

            // 开新线程
            final Channel channel_1 = future_1.channel();
            Thread thread = new Thread(() -> {
                for (int i = 0; i < 5; i++) {
                    try {
                        byte[] bytes = "来自客户端的消息".getBytes("UTF-8");
                        ByteBuf buf_1 = channel_1.alloc().buffer().writeBytes(bytes);
                        channel_1.writeAndFlush(buf_1);
                        Thread.sleep(1000);
                    } catch (UnsupportedEncodingException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
