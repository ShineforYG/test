package com.shine.easy_coding;

public class C2P75_StaticInit {
    public static void main(String[] args) {
        new Son();
        System.out.println("-----------------");
        new Son();
    }
}

class Parent{
    public int age;
    static {
        System.out.println("Parent  静态代码块");
    }
    public Parent(){
        System.out.println("Parent 构造方法");
    }
}

class Son extends Parent{
    public int age;
    static {
        System.out.println("Son 静态代码块");
    }
    public Son(){
        System.out.println("Son 构造方法");
    }
}
