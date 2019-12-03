package com.shine.netty.intergration.commend;

public class HeartBeatCmd extends Cmd {
    public final static String HEART = "SHB";

    public static String getHeartCmd(int port, boolean busy) {
        StringBuilder sb = new StringBuilder();
        sb.append(HEART).append(BLANK);
        sb.append(port).append(BLANK);
        sb.append(System.currentTimeMillis()).append(BLANK);
        sb.append(busy);
        return sb.toString();
    }
}
