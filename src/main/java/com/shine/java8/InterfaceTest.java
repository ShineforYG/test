package com.shine.java8;

public interface InterfaceTest {
    default void print() {
        System.out.println("hello world!");
    }

    static void print_2() {
        System.out.println("hello");
    }
}
