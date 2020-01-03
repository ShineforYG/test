package com.shine.netty.thread;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class OutTime {
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static final ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private static final ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
    private static final long OUT_TIME = 1000;
    private static long lastTime = Calendar.getInstance().getTimeInMillis();

    public static void update() {
        writeLock.lock();
        lastTime = Calendar.getInstance().getTimeInMillis();
        System.out.println(lastTime);
        writeLock.unlock();
    }

    public static long getLastTime() {
        readLock.lock();
        long last = lastTime;
        readLock.unlock();
        return last;
    }

    public static long getOutTime() {
        return OUT_TIME;
    }
}
