package com.shine.jvm;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 类加载
 */
public class ClassLoad {
    public static void main(String[] args) {
        testA();
    }

    static void testA() {
        System.out.println(A.NUMBER);
    }

    static void testB() {
        System.out.println(Bb.a);
    }
}

/**
 * 调用final常量不会加载类
 */
class A {
    public final static int NUMBER = 1000_0000;

    //    public  static int NUMBER = 1000_0000;
    static {
        System.out.println(123);
    }
}

/**
 * 调用父类字段, 不会触发子类的初始化函数
 */
class B {
    static {
        System.out.println("this is parent");
    }

    public static int a = 0;
}

class Bb extends B {
    static {
        System.out.println("this is children");
    }
}

/**
 * 类加载
 */
class Loader {
    public static void main(String[] args) throws ClassNotFoundException {
        Class clz = Class.forName("java.lang.String");
        Method[] methods = clz.getMethods();
        for (Method method : methods) {
            String mod = Modifier.toString(method.getModifiers());
            System.out.print(mod + " " + method.getName() + "(");

            Class<?>[] ps = method.getParameterTypes();
            if (ps.length == 0) System.out.println(")");

            for (int i = 0; i < ps.length; i++) {
                char end = (i == ps.length - 1) ? ')' : ',';
                System.out.print(ps[i].getSimpleName() + end);
            }
            System.out.println();
        }

    }
}

/**
 * 类初始化
 */
class SimpleStatic {
    public static int id = 1;
    public static int number;

    static {
        System.out.println("先加载父类");
        number = 4;
    }
}

class ChildStatic extends SimpleStatic {
    static {
        System.out.println("然后是子类");
        number = 2;
    }

    public static void main(String[] args) {
//        System.out.println(number);
    }
}

class StaticA {
    static {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Class.forName("com.shine.jvm.StaticB");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("A is ok");
    }
}

class StaticB {
    static {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Class.forName("com.shine.jvm.StaticA");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("B is ok");
    }
}

class StaticDeadLockMain extends Thread {
    private char flag;

    public StaticDeadLockMain(char flag) {
        this.flag = flag;
        this.setName("Thread" + flag);
    }

    @Override
    public void run() {
        try {
            Class.forName("com.shine.jvm.Static" + flag);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        StaticDeadLockMain lockMainA = new StaticDeadLockMain('A');
        StaticDeadLockMain lockMainB = new StaticDeadLockMain('B');
        lockMainA.start();
        lockMainB.start();
    }
}