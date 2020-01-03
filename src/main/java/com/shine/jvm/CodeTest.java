package com.shine.jvm;

public class CodeTest {
//    public void print3(char[] chars, short[] shorts){
//        System.out.println(shorts[0]);
//        System.out.println(chars[0]);
//    }
//
//    public void print4(char[] cs, int[] is) {
//        int i, j, k, x;
//        x = 99;
//        is[0] = 77;
//    }
//
//    public void print5(int i) {
//        new Object();
////        Object object = new Object();
////        object.toString();
//    }
//
//    public void print6(int i) {
//        long l = i;
//        ;
//        float f = l;
//        int j = (int) l;
//    }
//
//    public void print7(byte b) {
//        int k = b;
//        long l = b;
//    }
//
//    public void print8() {
//        int a = 1;
//        a++;
//        a += 10;
//        a = a + 10;
//    }
//
//    public void print9() {
////        int[] ints = new int[10];
//        int[][][] ints1 = new int[10][10][10];
//    }
//
//    public void print10(Object object) {
//        boolean a = object instanceof String;
//    }
//
//    public void print11(Object object) {
//        String a = (String) object;
//    }
//
//    public void print12(int a) {
//        if (a == 1) {
//            System.out.println(1);
//        } else {
//            System.out.println(2);
//        }
//    }
//
//    public void print13(int a) {
//        switch (a) {
//            case 1:
//                System.out.println(1);
//                break;
//            case 2:
//                System.out.println(2);
//                break;
//            case 3:
//                System.out.println(3);
//                break;
//            case 4:
//                System.out.println(4);
//        }
//    }
//
//    public void print14() {
//        int a = 1;
//        int b = 1;
//        if (a > 2) {
//            a++;
//        }
//    }
//
//    public synchronized void print15(){
//
//    }

    public void print16(){
        synchronized (this){
            System.out.println(123);
        }
    }
}
