package com.shine.jvm;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.nio.ByteBuffer;
import java.util.HashMap;

/**
 * 启动参数
 */
class Test {
    public static void main(String[] args) {
        printJvmMemory();
    }

    // java -Xmx32M jvm.Test
    private static void printJvmMemory() {
        System.out.println("JVM的最大内存设置为" + (Runtime.getRuntime().maxMemory() / 1024 / 1024) + "M");
    }
}

/**
 * java堆
 */
class SimpleHeap {
    private int id;

    public SimpleHeap(int id) {
        this.id = id;
    }

    public void show() {
        System.out.println("My id is : " + id);
    }

    public static void main(String[] args) {
        SimpleHeap s1 = new SimpleHeap(1);
        SimpleHeap s2 = new SimpleHeap(2);
        s1.show();
        s2.show();
    }
}

/**
 * 测试heap, 用到参数-Xss128k,设置堆的空间
 */
class TestStackDeep {
    private static int num = 0;

    public static void main(String[] args) {
        try {
            recursion();
        } catch (Exception e) {
            System.out.println(num);
            e.printStackTrace();
        }
    }

    private static void recursion() {
        System.out.println(num++);
        try {
            recursion();
        } catch (Exception e) {

        }
    }
}

class Gc {
    public static void main(String[] args) {
        Gc gc = new Gc();
        gc.localvarGc5();
    }

    /**
     * 存放在Heap的老年代,不会被GC(没有失去引用)
     */
    public void localvarGc1() {
        byte[] a = new byte[6 * 1024 * 1024];
        System.gc();
    }

    /**
     * 失去引用 发生GC
     */
    public void localvarGc2() {
        byte[] a = new byte[10 * 1024 * 1024];
        a = null;
        System.gc();
    }

    /**
     * 引用a存放在局部变量表中, 虽然已经超出作用域, 但是局部变量表中的引用没有被替换,
     * 所以不会发生GC
     */
    public void localvarGc3() {
        {
            byte[] a = new byte[6 * 1024 * 1024];
        }
        System.gc();
    }

    /**
     * 引用a超出局部变量表, c将a的slot复用, 发生GC
     */
    public void localvarGc4() {
        {
            byte[] a = new byte[6 * 1024 * 1024];
        }
        int c = 10;
        System.gc();
    }

    /**
     * Gc1()方法已经出栈, 所以发生GC
     */
    public void localvarGc5() {
        localvarGc1();
        System.gc();
    }
}

/**
 * 通过逃逸分析, 将对象打散成变量存放在栈上
 * -server
 * -Xmx10m
 * -Xms10m
 * -XX:-DoEscapeAnalysis
 * -XX:+PrintGC
 * -XX:-UseTLAB
 * -XX:-EliminateAllocations
 */
class OnStackTest {
    public static class User {
        public int id = 0;
        public String name = "";
    }

    public static void alloc() {
        User user = new User();
        user.id = 5;
        user.name = "geym";
    }

    public static void main(String[] args) {
        long b = System.currentTimeMillis();
        for (int i = 0; i < 10_0000_0000; i++) {
            alloc();
        }
        long e = System.currentTimeMillis();
        System.out.println(e - b);
    }
}

class PermTest {
    public static void main(String[] args) {
        int i = 0;
        try {
            for (i = 0; i < 100000; i++) {
                CglibBean bean = new CglibBean("com/shine/jvm" + i, new HashMap());
            }
        } catch (Exception e) {
            System.out.println("total create count:" + i);
            throw e;
        }
    }
}

/**
 * VM option '+PrintVMOptions'
 */
class PrintArgs {
    public static void main(String[] args) {

    }
}

/**
 * -Xmx20m -Xms5m -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:+UseSerialGC
 */
class HeapAlloc {
    public static void main(String[] args) {
        System.out.print("maxMemory = ");
        System.out.println(Runtime.getRuntime().maxMemory() / 1024.0 / 1024.0 + "MB");
        System.out.print("free mem = ");
        System.out.println(Runtime.getRuntime().freeMemory() / 1024.0 / 1024.0 + "MB");
        System.out.print("total mem = ");
        System.out.println(Runtime.getRuntime().totalMemory() / 1024.0 / 1024.0 + "MB");

        byte[] b = new byte[1 * 1024 * 1024];
        System.out.println("分配了1M空间给数组");

        System.out.print("maxMemory = ");
        System.out.println(Runtime.getRuntime().maxMemory() / 1024.0 / 1024.0 + "MB");
        System.out.print("free mem = ");
        System.out.println(Runtime.getRuntime().freeMemory() / 1024.0 / 1024.0 + "MB");
        System.out.print("total mem = ");
        System.out.println(Runtime.getRuntime().totalMemory() / 1024.0 / 1024.0 + "MB");

        b = new byte[4 * 1024 * 1024];
        System.out.println("分配了4M空间给数组");

        System.out.print("maxMemory = ");
        System.out.println(Runtime.getRuntime().maxMemory() / 1024.0 / 1024.0 + "MB");
        System.out.print("free mem = ");
        System.out.println(Runtime.getRuntime().freeMemory() / 1024.0 / 1024.0 + "MB");
        System.out.print("total mem = ");
        System.out.println(Runtime.getRuntime().totalMemory() / 1024.0 / 1024.0 + "MB");
    }
}

/**
 * 测试新生代参数
 * -Xmx20m -Xms20m -Xmn1m -XX:SurvivorRatio=2 -XX:+PrintGCDetails
 */
class NewSizeDemo {
    public static void main(String[] args) {
        byte[] b = null;
        for (int i = 0; i < 10; i++) {
            b = new byte[1 * 1024 * 1024];
        }
    }
}

class AccessDirectBuffer {
    public static void main(String[] args) {
        AccessDirectBuffer alloc = new AccessDirectBuffer();
        alloc.bufferAccess();
        alloc.directAccess();

        alloc.bufferAccess();
        alloc.directAccess();
    }

    public void directAccess() {
        long starttime = System.currentTimeMillis();
        ByteBuffer b = ByteBuffer.allocateDirect(500);
        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 99; j++)
                b.putInt(j);
            b.flip();
            for (int j = 0; j < 99; j++)
                b.getInt();
            b.clear();
        }
        long endtime = System.currentTimeMillis();
        System.out.println("testDirectWrite:" + (endtime - starttime));
    }

    public void bufferAccess() {
        long starttime = System.currentTimeMillis();
        ByteBuffer b = ByteBuffer.allocate(500);
        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 99; j++)
                b.putInt(j);
            b.flip();
            for (int j = 0; j < 99; j++)
                b.getInt();
            b.clear();
        }
        long endtime = System.currentTimeMillis();
        System.out.println("testBufferWrite:" + (endtime - starttime));
    }
}

class HoldCPUMain {
    public static void main(String[] args) {
        new Thread(new HoldCPUTask()).start();
        new Thread(new LazyTask()).start();
        new Thread(new LazyTask()).start();
        new Thread(new LazyTask()).start();
    }

    public static class HoldCPUTask implements Runnable {
        @Override
        public void run() {
            while (true) {
                double a = Math.random() * Math.random();
            }
        }
    }

    public static class LazyTask implements Runnable {
        public void run() {
            try {
                while (true) {
                    Thread.sleep(1000);
                }
            } catch (Exception e) {

            }
        }
    }
}

class HelloWorld{
    public static void main(String[] args) {
        try {
            Thread.sleep(1000);
            System.out.println(123);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}




class SoftRefQ {
    static ReferenceQueue<User> softQueue = null;

    public static void main(String[] args) throws InterruptedException {
        Thread t = new CheckRefQueue();
        t.setDaemon(true);
        t.start();
        User u = new User(1, "geym");
        softQueue = new ReferenceQueue<User>();
        UserSoftReference userSoftRef = new UserSoftReference(u, softQueue);
        u = null;
        System.out.println(userSoftRef.get());
        System.gc();
        //内存足够，不会被回收
        System.out.println("After GC:");
        System.out.println(userSoftRef.get());

        System.out.println("try to create byte array and GC");
        byte[] b = new byte[1000 * 1000 * 5];
        System.gc();
        System.out.println(userSoftRef.get());

        Thread.sleep(1000);
    }

    public static class User {
        public int id;
        public String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "[id=" + String.valueOf(id) + ",name=" + name + "]";
        }
    }

    public static class CheckRefQueue extends Thread {
        @Override
        public void run() {
            while (true) {
                if (softQueue != null) {
                    UserSoftReference obj = null;
                    try {
                        obj = (UserSoftReference) softQueue.remove();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (obj != null)
                        System.out.println("user id " + obj.uid + " is delete");
                }
            }
        }
    }

    public static class UserSoftReference extends SoftReference<User> {
        int uid;

        public UserSoftReference(User referent, ReferenceQueue<? super User> q) {
            super(referent, q);
            uid = referent.id;
        }
    }
}

/**
 * -XX:-UseTLAB -Xcomp -XX:-BackgroundCompilation -XX:-DoEscapeAnalysis -server
 */
class UseTLAB {
    public static void alloc() {
        byte[] b = new byte[2];
        b[0] = 1;

    }

    public static void main(String args[]) {
        long b = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            alloc();
        }
        long e = System.currentTimeMillis();
        System.out.println(e - b);
    }
}
