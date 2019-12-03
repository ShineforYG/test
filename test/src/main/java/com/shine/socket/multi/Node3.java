package com.shine.socket.multi;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Node3 {
    private static int port = 8000;
    private static String address = "228.0.0.4";

    public static void main(String[] args) throws IOException {
        InetAddress group = InetAddress.getByName(address);
        MulticastSocket msr = new MulticastSocket(port);
        msr.joinGroup(group);
        byte[] buffer = new byte[1024];
        while (true) {
            DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
            msr.receive(dp);
            String s = new String(dp.getData(), 0, dp.getLength());
            System.out.println("receive from node1: " + s);
        }
    }
}
