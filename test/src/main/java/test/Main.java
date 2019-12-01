package test;

import java.util.concurrent.ConcurrentSkipListMap;

public class Main {
    public static void main(String[] args) {
        ConcurrentSkipListMap<Integer, String> map = new ConcurrentSkipListMap<>();

    }

    private static void test1() {
        WriteReadLockTest test = new WriteReadLockTest();
        test.getNum();
        test.setNum(10);
    }
}
