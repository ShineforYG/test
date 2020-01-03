package com.shine.asm;

import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

public class AddSecurityCheckClassAdapter extends ClassVisitor {
    public AddSecurityCheckClassAdapter(ClassVisitor classVisitor) {
        super(Opcodes.ASM5, classVisitor);
    }

    public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        MethodVisitor wrappedMv = mv;

        if (mv != null) {
            if (name.equals("operation")) {
                wrappedMv = new AddSecurityCheckMethodAdapter(mv);
            }
        }
        return wrappedMv;
    }
}
