package com.shine.netty.intergration.commend;

/**
 * 客户端相关指令
 */
public class LoginCmd extends Cmd {

    public final static String LOGIN = "SLG";
    public final static String SUCCESS = "SLS";

    /**
     * 返回注册指令 格式：SLG 监听端口 时间戳 空闲状态 例如：SLG 8081 00000000111110 false
     *
     * @param port：监听端口
     * @param busy：空闲状态 true：繁忙 false：空闲
     * @return 注册指令
     */
    public static String getLoginCmd(int port, boolean busy) {
        StringBuilder sb = new StringBuilder();
        sb.append(LOGIN).append(BLANK);
        sb.append(port).append(BLANK);
        sb.append(System.currentTimeMillis()).append(BLANK);
        sb.append(busy);
        return sb.toString();
    }
}
