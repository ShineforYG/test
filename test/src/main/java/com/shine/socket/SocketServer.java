package com.shine.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        Socket socket = serverSocket.accept();
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        System.out.println("服务器接收到的是: " + dis.readUTF());
//        dos.writeUTF("来自服务器的问候");
        socket.close();
        serverSocket.close();
    }
}
