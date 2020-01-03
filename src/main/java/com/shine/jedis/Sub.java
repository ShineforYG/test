package com.shine.jedis;

import redis.clients.jedis.Jedis;

import java.util.Random;
import java.util.TreeMap;

public class Sub {
    private static final String HOST = "192.168.74.133";
    private static final int PORT = 6379;

    public static void main(String[] args) {
        Jedis jedis = new Jedis(HOST, PORT);
        while (true) {
            Random rn = new Random();
            String msg = String.valueOf(rn.nextFloat());
            jedis.publish("chat", msg);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
