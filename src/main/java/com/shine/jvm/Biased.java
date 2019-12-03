package com.shine.jvm;

import java.util.List;
import java.util.Vector;

/**
 * -XX:+UseBiasedLocking -XX:BiasedLockingStartupDelay=0 -client -Xmx512m -Xms512m
 * -XX:-UseBiasedLocking -XX:BiasedLockingStartupDelay=0 -client -Xmx512m -Xms512m
 */
public class Biased {
    public static List<Integer> list = new Vector<>();

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        int count = 0;
        int startnum = 0;
        for (;count < 1000_0000;) {
            list.add(startnum);
            startnum++;
            count++;
        }
        long end  = System.currentTimeMillis();
        System.out.println(end - begin);
    }
}
