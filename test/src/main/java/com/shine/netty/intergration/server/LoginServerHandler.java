package com.shine.netty.intergration.server;

import com.shine.netty.intergration.commend.Cmd;
import com.shine.netty.intergration.commend.LoginCmd;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * 用于处理客户端信息注册的Handler
 */
public class LoginServerHandler extends ChannelInboundHandlerAdapter {
    ClientStateMap stateMap = ClientStateMap.getStateMap();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        StringBuilder sb = new StringBuilder();

        if (buf.isDirect()) {
            int len = buf.readableBytes();
            byte[] array = new byte[len];
            buf.getBytes(buf.readerIndex(), array);
            sb.append(new String(array, "UTF-8"));
        } else {
            byte[] array = buf.array();
            int offset = buf.arrayOffset() + buf.readerIndex();
            int len = buf.readableBytes();
            sb.append(new String(array, offset, len, "UTF-8"));
        }
        System.out.println(sb.toString());
        String[] msgs = sb.toString().split(Cmd.BLANK);
        if (msgs[0].equals(LoginCmd.LOGIN)) {
            boolean rst = false;
            try {
                InetSocketAddress ipSocket = (InetSocketAddress) ctx.channel().remoteAddress();
                InetAddress address = ipSocket.getAddress();
                rst = stateMap.update(address, msgs[1], msgs[2], msgs[3]);
                // todo： 回传成功
            } catch (Exception e) {
                System.out.println("服务注册失败");
            }
            if (rst) {
                System.out.println("注册成功");
                ctx.writeAndFlush(LoginCmd.SUCCESS.getBytes("UTF-8"));
            }
        }
        super.channelRead(ctx, msg);
        
    }
}
