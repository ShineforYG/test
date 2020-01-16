package com.shine.easy_coding;

import java.util.List;
import java.util.Map;

public class C2P85_OverloadMethods {
    public void overloadMethod() {
        System.out.println("无参方法");
    }

    public void methodForOverload(int param) {
        System.out.println("int");
    }

    public void methodForOverload(Integer param) {
        System.out.println("Integer");
    }

    public void methodForOverload(Integer... param) {
        System.out.println("Integer ...");
    }

    public void methodForOverload(Object param) {
        System.out.println("Object");
    }

    public static void main(String[] args) {
        C2P85_OverloadMethods methods = new C2P85_OverloadMethods();
        Integer integer = 2;
        methods.methodForOverload(integer, 14);
    }
}
