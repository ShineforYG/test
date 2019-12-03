package com.shine.netty.intergration.client;

import com.shine.netty.intergration.commend.LoginCmd;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class LoginClientHandler extends ChannelInboundHandlerAdapter {

    private final int port;

    public LoginClientHandler(int port) {
        this.port = port;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String cmd = String.valueOf(msg);
        System.out.println(cmd);
        String[] msgs = cmd.split(" ");
        if (msgs[0].equals(LoginCmd.SUCCESS)) {
            System.out.println("注册成功，正在发送心跳信号");
            ctx.pipeline().addFirst(new HeartBeatClientHandler(port));
            ctx.pipeline().remove("login");
        }
//        super.channelRead(ctx, msg);
    }
}
