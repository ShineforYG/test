package com.shine.jvm;

/**
 * bootstrap类加载器采用c语言实现, 无法在java中显示
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        System.out.println(String.class.getClassLoader() );
    }
}

class PrintClassLoaderTree{
    public static void main(String[] args) {
        ClassLoader classLoader = PrintClassLoaderTree.class.getClassLoader();
        while (classLoader != null){
            System.out.println(classLoader);
            classLoader = classLoader.getParent();
        }
    }
}

class FindClassOrder{
    public static void main(String[] args) {
        System.out.println(FindClassOrder.class.getClassLoader());
    }
}
