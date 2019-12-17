package com.github.zszlly.recorder.asm;

import com.github.zszlly.mark.NoTestMark;
import org.objectweb.asm.*;

import java.util.*;

import static org.objectweb.asm.Opcodes.ACC_STATIC;

public class NoTestClassVisitor extends ClassVisitor {

    private boolean isNoTest = false;
    private final Map<String, Set<String>> methods;
    private String classInternalName;
    private List<FieldDescription> fieldDescriptions = new LinkedList<>();

    public NoTestClassVisitor(int api, ClassVisitor cv, Map<String, Set<String>> methods) {
        super(api, cv);
        this.methods = methods;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
//        for (String interf : interfaces) {
//            if (NoTestMark.INTERNAL_NAME.equals(interf)) {
//                isNoTest = true;
//                break;
//            }
//        }
//        if (isNoTest) {
//            super.visit(version, access, name, signature, superName, interfaces);
//            return;
//        }
//        String[] newInterfaces = Arrays.copyOf(interfaces, interfaces.length + 1);
//        newInterfaces[interfaces.length] = NoTestMark.INTERNAL_NAME;
//        super.visit(version, access, name, signature, superName, newInterfaces);
        super.visit(version, access, name, signature, superName, interfaces);
        classInternalName = name;
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        fieldDescriptions.add(new FieldDescription(name, classInternalName, desc));
        return super.visitField(access, name, desc, signature, value);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        if (isNoTest) {
            return mv;
        }
        Set<String> methodDescriptions = methods.get(name);
        if (methodDescriptions != null && methodDescriptions.contains(desc)) {
            return new NoTestMethodVisitor(Opcodes.ASM5, access, desc, mv, new MethodDescription(name, classInternalName, desc), fieldDescriptions, (access & ACC_STATIC) != 0);
        }
        return mv;
    }
}
