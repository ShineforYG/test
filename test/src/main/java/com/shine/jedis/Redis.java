package com.shine.jedis;

import redis.clients.jedis.Jedis;

import java.util.Random;

public class Redis {
    private static final String HOST = "192.168.74.133";
    private static final int PORT = 6379;

    public static void main(String[] args) {
        testSet();
    }

    private static void testSet() {
        Thread[] threads = new Thread[10];
        for (Thread thread : threads) {
            thread = new Thread() {
                @Override
                public void run() {
                    Jedis jedis = new Jedis(HOST, PORT);
                    int size = Integer.MAX_VALUE;
                    Random random = new Random();
                    for (int i = 0; i < size; i++) {
                        double tmp = random.nextDouble();
                        System.out.println(jedis.sadd("myS" + tmp, "mkS-" + tmp));
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            thread.start();
        }
    }

    private static void testPing() {
        Jedis jedis = new Jedis(HOST, PORT);
        System.out.println(jedis.ping());
    }

    private static void testHLL() {
        Jedis jedis = new Jedis(HOST, PORT);

        int size = 100000;

        for (int i = 0; i < size; i++) {
            jedis.pfadd("myk", "mky-" + i);
        }
        long total = jedis.pfcount("myk");
        System.out.println(String.format("统计个数: %s", total));
        System.out.println(String.format("正确率: %s", (total / size)));
        System.out.println(String.format("误差率: %s", 1 - (total / size)));
        jedis.close();
    }
}
