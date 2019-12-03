package com.shine.netty.thread;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class CheckerTask implements TimerTask {
    static HashedWheelTimer timer = new HashedWheelTimer();

    static void launch() {
        timer.newTimeout(new CheckerTask(), OutTime.getOutTime(), TimeUnit.MILLISECONDS);
    }

    @Override
    public void run(Timeout timeout) throws Exception {
        long now = Calendar.getInstance().getTimeInMillis();
        long last = OutTime.getLastTime();
        long tar = last + OutTime.getOutTime();
        if (now > tar) {
            System.out.println("超时");
//            timer.stop();
        } else {
            System.out.println("没有超时");
            long delay = tar - now;
            timer.newTimeout(this::run, delay, TimeUnit.MILLISECONDS);
        }
    }
}
