package com.shine.test;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class WriteReadLockTest {
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    int num = 1;

    public int getNum() {
        readLock.lock();
        int rst = this.num;
        System.out.println("get read lock");
        readLock.unlock();
        return rst;
    }

    public void setNum(int num) {
        writeLock.lock();
        this.num = num;
        System.out.println("get write lock");
        System.out.println(getNum());
        writeLock.unlock();
    }
}
