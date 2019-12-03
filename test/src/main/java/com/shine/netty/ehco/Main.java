package com.shine.netty.ehco;

public class Main {
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
}
