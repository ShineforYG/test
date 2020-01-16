package com.shine.easy_coding;

public class C2P89_GenericDefinitionDemo {

    public  static  <T> String get(T t) {
        return t.toString();
    }

    public static void main(String[] args) {
        System.out.println(get(1));

    }
}
