package com.shine.netty.test;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.util.Date;

public class Node {
    private static final int MAX_RETRY = 5;
    private static final String LOCALHOST = "127.0.0.1";

//    public static final NodeState STATE = NodeState.getInstance(); // 节点状态
    private final InetSocketAddress selfAddress; // 自己的地址
    public final ServerBootstrap listener;
    public final Bootstrap sender;

    public static volatile Node INSTANCE = null;

    // DCL双重检查锁定
    public static Node getNode(int port) {
        if (INSTANCE == null) {
            synchronized (Node.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Node(port);
                }
            }
        }
        return INSTANCE;
    }

    private Node(int port) {
        this.selfAddress = new InetSocketAddress(LOCALHOST, port);
        this.listener = new ServerBootstrap();
        this.sender = new Bootstrap();
        this.defaultInit(port);
    }

    /**
     * Netty节点初始化，基于角色进行初始化
     */
    private void defaultInit(int port) {
        this.initListener(port);
//        this.initSender();
    }

    /**
     * 初始化监听端口
     *
     * @param port：监听端口号
     */
    private void initListener(int port) {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        listener.group(boss, worker);
        listener.channel(NioServerSocketChannel.class);
        listener.localAddress(port);
        listener.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel channel) throws Exception {
//                channel.pipeline().addLast(new EchoServerHandler());
            }
        });
        listener.option(ChannelOption.SO_KEEPALIVE, true); // 设置长连接
        listener.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        bind(this.listener, port);
    }

    /**
     * 绑定监听端口
     *
     * @param serverBootstrap：监听Bootstrap
     * @param port：监听端口号
     */
    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": 端口[" + port + "]绑定成功!");
            } else {
                System.err.println("端口[" + port + "]绑定失败!");
            }
        });
    }

    /**
     * 初始化传输端口
     */
    private void initSender() {
        NioEventLoopGroup senderWorker = new NioEventLoopGroup();
        sender.group(senderWorker);
        sender.channel(NioSocketChannel.class);
        sender.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {

            }
        });
        sender.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
    }
}
