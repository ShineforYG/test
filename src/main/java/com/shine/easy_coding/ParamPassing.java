package com.shine.easy_coding;

public class ParamPassing {
    private static int intStatic = 222;
    private static String StringStatic = "Hello World";
    private static StringBuilder stringBuilder = new StringBuilder("hello");
    public static void main(String[] args) {
//        method(intStatic);
//        System.out.println(intStatic);
//        method();
//        System.out.println(intStatic);
        method(StringStatic);
        System.out.println(StringStatic);
        method(stringBuilder);
        System.out.println(stringBuilder.toString());
    }

    public static void method(int intStatic){
        intStatic = 777;
    }

    public static void method(){
        intStatic = 888;
    }

    public static void method(String StringStatic){
        StringStatic = " Bye";
    }

    public static void method(StringBuilder StringStatic){
        StringStatic.append("-End");
    }
}
