package com.shine.asm;

import jdk.internal.org.objectweb.asm.Label;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

public class AddSecurityCheckMethodAdapter extends MethodVisitor {
    public AddSecurityCheckMethodAdapter(MethodVisitor methodVisitor) {
        super(Opcodes.ASM5, methodVisitor);
    }

    public void visitCode() {
        Label continueLabel = new Label();
        visitMethodInsn(Opcodes.INVOKESTATIC, "com/shine/asm/SecurityChecker", "checkSecurity", "()Z");
        visitJumpInsn(Opcodes.IFNE, continueLabel);
        visitInsn(Opcodes.RETURN);
        visitLabel(continueLabel);
        super.visitCode();
    }
}
