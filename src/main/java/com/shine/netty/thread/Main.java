package com.shine.netty.thread;

public class Main {
    public static void main(String[] args) {
        Keepper keepper = new Keepper();
        CheckerTask.launch();
        keepper.start();
    }
}
