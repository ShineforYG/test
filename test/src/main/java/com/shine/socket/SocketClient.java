package com.shine.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8080);
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        dos.writeUTF("我是客户端,请求连接!");
        System.out.println(dis.readUTF());
        socket.close();
    }
}
