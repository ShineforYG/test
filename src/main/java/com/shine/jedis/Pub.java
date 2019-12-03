package com.shine.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class Pub {
    private static final String HOST = "192.168.74.133";
    private static final int PORT = 6379;

    public static void main(String[] args) {
        Jedis jedis = new Jedis(HOST, PORT);
        jedis.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.println(channel + "------" + message);
            }
        }, "chat1");
    }
}
