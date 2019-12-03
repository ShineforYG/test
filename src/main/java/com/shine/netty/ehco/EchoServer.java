package com.shine.netty.ehco;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class EchoServer {
    public static void main(String[] args) {
//        Thread thread = new Thread(() -> {
//            for (;true;){
//                System.out.println(4576);
//                for (Channel channel : ChannelSet.SET) {
//                    byte[] bytes = new byte[0];
//                    try {
//                        bytes = "心跳".getBytes("UTF-8");
//                    } catch (UnsupportedEncodingException e) {
//                        e.printStackTrace();
//                    }
//                    ByteBuf buf_1 = channel.alloc().buffer().writeBytes(bytes);
//                    channel.writeAndFlush(buf_1);
//                }
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        thread.start();

        int port = EchoConfig.SERVER_PORT;
        EchoServer server = new EchoServer(9000);
        server.runServer();
    }

    private final int serverPort;
    ServerBootstrap serverBootstrap = new ServerBootstrap();

    public EchoServer(int port) {
        this.serverPort = port;
    }

    public void runServer() {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            serverBootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(NioSocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new IdleHandler());
                    socketChannel.pipeline().addLast(new EchoServerHandler());
                }
            });
            serverBootstrap.group(boss, worker);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.localAddress(serverPort);
            serverBootstrap.option(ChannelOption.SO_KEEPALIVE, true); // 设置长连接
            serverBootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
//            ChannelFuture closeFuture = channelFuture.channel().closeFuture();
//            closeFuture.sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
//            worker.shutdownGracefully();
//            boss.shutdownGracefully();
        }
    }
}
