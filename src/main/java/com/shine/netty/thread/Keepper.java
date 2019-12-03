package com.shine.netty.thread;

import java.util.Random;

public class Keepper extends Thread {
    @Override
    public void run() {
        for (; true; ) {
            OutTime.update();
            try {
                Thread.sleep((long) (100 + new Random().nextDouble() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
