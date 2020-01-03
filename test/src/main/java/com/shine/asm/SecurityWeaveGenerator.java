package com.shine.asm;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;
import org.objectweb.asm.ClassAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class SecurityWeaveGenerator {
    public static void main(String[] args) throws IOException {
        String className = Account.class.getName();
        // asm
        ClassReader cr = new ClassReader(className);
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        AddSecurityCheckClassAdapter classAdapter = new AddSecurityCheckClassAdapter(cw);
        cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
        byte[] data = cw.toByteArray();
        File file = new File("E:\\IdeaProjects\\test\\test\\target\\classes\\" + className.replaceAll("\\.", "/") + ".class");
        FileOutputStream fout = new FileOutputStream(file);
        fout.write(data);
        fout.close();
        System.out.println(Arrays.toString(data));
    }
}
