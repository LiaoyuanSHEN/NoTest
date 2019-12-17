package com.github.zszlly.agent;

import org.objectweb.asm.*;

public class GeneratedClassADump implements Opcodes {

    public static byte[] dump() throws Exception {
        ClassWriter classWriter = new ClassWriter(0);
        FieldVisitor fieldVisitor;
        MethodVisitor methodVisitor;
        AnnotationVisitor annotationVisitor0;

        classWriter.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "com/github/zszlly/agent/GeneratedClassA", null, "java/lang/Object", null);

        classWriter.visitSource("GeneratedClassA.java", null);

        classWriter.visitInnerClass("java/lang/invoke/MethodHandles$Lookup", "java/lang/invoke/MethodHandles", "Lookup", ACC_PUBLIC | ACC_FINAL | ACC_STATIC);

        {
            fieldVisitor = classWriter.visitField(ACC_PRIVATE, "field", "Lcom/github/zszlly/builder/CaseBuilder;", null, null);
            fieldVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(15, label0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            methodVisitor.visitInsn(RETURN);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLocalVariable("this", "Lcom/github/zszlly/agent/GeneratedClassA;", null, label0, label1, 0);
            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "boxing", "(ZCBSIJFD)V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(20, label0);
            methodVisitor.visitVarInsn(ILOAD, 1);
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;", false);
            methodVisitor.visitInsn(POP);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(21, label1);
            methodVisitor.visitVarInsn(ILOAD, 2);
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;", false);
            methodVisitor.visitInsn(POP);
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLineNumber(22, label2);
            methodVisitor.visitVarInsn(ILOAD, 3);
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;", false);
            methodVisitor.visitInsn(POP);
            Label label3 = new Label();
            methodVisitor.visitLabel(label3);
            methodVisitor.visitLineNumber(23, label3);
            methodVisitor.visitVarInsn(ILOAD, 4);
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;", false);
            methodVisitor.visitInsn(POP);
            Label label4 = new Label();
            methodVisitor.visitLabel(label4);
            methodVisitor.visitLineNumber(24, label4);
            methodVisitor.visitVarInsn(ILOAD, 5);
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
            methodVisitor.visitInsn(POP);
            Label label5 = new Label();
            methodVisitor.visitLabel(label5);
            methodVisitor.visitLineNumber(25, label5);
            methodVisitor.visitVarInsn(LLOAD, 6);
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;", false);
            methodVisitor.visitInsn(POP);
            Label label6 = new Label();
            methodVisitor.visitLabel(label6);
            methodVisitor.visitLineNumber(26, label6);
            methodVisitor.visitVarInsn(FLOAD, 8);
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;", false);
            methodVisitor.visitInsn(POP);
            Label label7 = new Label();
            methodVisitor.visitLabel(label7);
            methodVisitor.visitLineNumber(27, label7);
            methodVisitor.visitVarInsn(DLOAD, 9);
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;", false);
            methodVisitor.visitInsn(POP);
            Label label8 = new Label();
            methodVisitor.visitLabel(label8);
            methodVisitor.visitLineNumber(28, label8);
            methodVisitor.visitInsn(RETURN);
            Label label9 = new Label();
            methodVisitor.visitLabel(label9);
            methodVisitor.visitLocalVariable("this", "Lcom/github/zszlly/agent/GeneratedClassA;", null, label0, label9, 0);
            methodVisitor.visitLocalVariable("v1", "Z", null, label0, label9, 1);
            methodVisitor.visitLocalVariable("v2", "C", null, label0, label9, 2);
            methodVisitor.visitLocalVariable("v3", "B", null, label0, label9, 3);
            methodVisitor.visitLocalVariable("v4", "S", null, label0, label9, 4);
            methodVisitor.visitLocalVariable("v5", "I", null, label0, label9, 5);
            methodVisitor.visitLocalVariable("v6", "J", null, label0, label9, 6);
            methodVisitor.visitLocalVariable("v7", "F", null, label0, label9, 8);
            methodVisitor.visitLocalVariable("v8", "D", null, label0, label9, 9);
            methodVisitor.visitMaxs(2, 11);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "unboxing", "(Ljava/lang/Boolean;Ljava/lang/Character;Ljava/lang/Byte;Ljava/lang/Short;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/Float;Ljava/lang/Double;)V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(31, label0);
            methodVisitor.visitVarInsn(ALOAD, 1);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Boolean", "booleanValue", "()Z", false);
            methodVisitor.visitVarInsn(ISTORE, 9);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(32, label1);
            methodVisitor.visitVarInsn(ALOAD, 2);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Character", "charValue", "()C", false);
            methodVisitor.visitVarInsn(ISTORE, 10);
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLineNumber(33, label2);
            methodVisitor.visitVarInsn(ALOAD, 3);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Byte", "byteValue", "()B", false);
            methodVisitor.visitVarInsn(ISTORE, 11);
            Label label3 = new Label();
            methodVisitor.visitLabel(label3);
            methodVisitor.visitLineNumber(34, label3);
            methodVisitor.visitVarInsn(ALOAD, 4);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Short", "shortValue", "()S", false);
            methodVisitor.visitVarInsn(ISTORE, 12);
            Label label4 = new Label();
            methodVisitor.visitLabel(label4);
            methodVisitor.visitLineNumber(35, label4);
            methodVisitor.visitVarInsn(ALOAD, 5);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Integer", "intValue", "()I", false);
            methodVisitor.visitVarInsn(ISTORE, 13);
            Label label5 = new Label();
            methodVisitor.visitLabel(label5);
            methodVisitor.visitLineNumber(36, label5);
            methodVisitor.visitVarInsn(ALOAD, 6);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Long", "longValue", "()J", false);
            methodVisitor.visitVarInsn(LSTORE, 14);
            Label label6 = new Label();
            methodVisitor.visitLabel(label6);
            methodVisitor.visitLineNumber(37, label6);
            methodVisitor.visitVarInsn(ALOAD, 7);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Float", "floatValue", "()F", false);
            methodVisitor.visitVarInsn(FSTORE, 16);
            Label label7 = new Label();
            methodVisitor.visitLabel(label7);
            methodVisitor.visitLineNumber(38, label7);
            methodVisitor.visitVarInsn(ALOAD, 8);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Double", "doubleValue", "()D", false);
            methodVisitor.visitVarInsn(DSTORE, 17);
            Label label8 = new Label();
            methodVisitor.visitLabel(label8);
            methodVisitor.visitLineNumber(39, label8);
            methodVisitor.visitInsn(RETURN);
            Label label9 = new Label();
            methodVisitor.visitLabel(label9);
            methodVisitor.visitLocalVariable("this", "Lcom/github/zszlly/agent/GeneratedClassA;", null, label0, label9, 0);
            methodVisitor.visitLocalVariable("v1", "Ljava/lang/Boolean;", null, label0, label9, 1);
            methodVisitor.visitLocalVariable("v2", "Ljava/lang/Character;", null, label0, label9, 2);
            methodVisitor.visitLocalVariable("v3", "Ljava/lang/Byte;", null, label0, label9, 3);
            methodVisitor.visitLocalVariable("v4", "Ljava/lang/Short;", null, label0, label9, 4);
            methodVisitor.visitLocalVariable("v5", "Ljava/lang/Integer;", null, label0, label9, 5);
            methodVisitor.visitLocalVariable("v6", "Ljava/lang/Long;", null, label0, label9, 6);
            methodVisitor.visitLocalVariable("v7", "Ljava/lang/Float;", null, label0, label9, 7);
            methodVisitor.visitLocalVariable("v8", "Ljava/lang/Double;", null, label0, label9, 8);
            methodVisitor.visitLocalVariable("vv1", "Z", null, label1, label9, 9);
            methodVisitor.visitLocalVariable("vv2", "C", null, label2, label9, 10);
            methodVisitor.visitLocalVariable("vv3", "B", null, label3, label9, 11);
            methodVisitor.visitLocalVariable("vv4", "S", null, label4, label9, 12);
            methodVisitor.visitLocalVariable("vv5", "I", null, label5, label9, 13);
            methodVisitor.visitLocalVariable("vv6", "J", null, label6, label9, 14);
            methodVisitor.visitLocalVariable("vv7", "F", null, label7, label9, 16);
            methodVisitor.visitLocalVariable("vv8", "D", null, label8, label9, 17);
            methodVisitor.visitMaxs(2, 19);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "array", "([[Ljava/lang/Integer;)V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(43, label0);
            methodVisitor.visitInsn(RETURN);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLocalVariable("this", "Lcom/github/zszlly/agent/GeneratedClassA;", null, label0, label1, 0);
            methodVisitor.visitLocalVariable("arr", "[[Ljava/lang/Integer;", null, label0, label1, 1);
            methodVisitor.visitMaxs(0, 2);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "invokeVirtual", "()V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(46, label0);
            methodVisitor.visitTypeInsn(NEW, "java/util/LinkedList");
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/util/LinkedList", "<init>", "()V", false);
            methodVisitor.visitVarInsn(ASTORE, 1);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(47, label1);
            methodVisitor.visitVarInsn(ALOAD, 1);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "add", "(Ljava/lang/Object;)Z", true);
            methodVisitor.visitInsn(POP);
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLineNumber(48, label2);
            methodVisitor.visitInsn(RETURN);
            Label label3 = new Label();
            methodVisitor.visitLabel(label3);
            methodVisitor.visitLocalVariable("this", "Lcom/github/zszlly/agent/GeneratedClassA;", null, label0, label3, 0);
            methodVisitor.visitLocalVariable("list", "Ljava/util/List;", "Ljava/util/List<Ljava/lang/Object;>;", label1, label3, 1);
            methodVisitor.visitMaxs(2, 2);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "getField", "()V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(51, label0);
            methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            methodVisitor.visitFieldInsn(GETSTATIC, "com/github/zszlly/util/NoTestUtils", "VOID_ARGUMENT", "Lcom/github/zszlly/model/VoidArgument;");
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/Object;)V", false);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(52, label1);
            methodVisitor.visitInsn(RETURN);
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLocalVariable("this", "Lcom/github/zszlly/agent/GeneratedClassA;", null, label0, label2, 0);
            methodVisitor.visitMaxs(2, 1);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "out", "()V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(55, label0);
            methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            methodVisitor.visitLdcInsn("123");
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(56, label1);
            methodVisitor.visitInsn(RETURN);
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLocalVariable("this", "Lcom/github/zszlly/agent/GeneratedClassA;", null, label0, label2, 0);
            methodVisitor.visitMaxs(2, 1);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "test", "(IDLjava/lang/String;Ljava/lang/Float;)D", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(59, label0);
            methodVisitor.visitVarInsn(ILOAD, 1);
            methodVisitor.visitInsn(I2D);
            methodVisitor.visitVarInsn(DLOAD, 2);
            methodVisitor.visitInsn(DADD);
            methodVisitor.visitVarInsn(ALOAD, 4);
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Double", "parseDouble", "(Ljava/lang/String;)D", false);
            methodVisitor.visitInsn(DADD);
            methodVisitor.visitVarInsn(ALOAD, 5);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Float", "floatValue", "()F", false);
            methodVisitor.visitInsn(F2D);
            methodVisitor.visitInsn(DADD);
            methodVisitor.visitInsn(DRETURN);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLocalVariable("this", "Lcom/github/zszlly/agent/GeneratedClassA;", null, label0, label1, 0);
            methodVisitor.visitLocalVariable("a", "I", null, label0, label1, 1);
            methodVisitor.visitLocalVariable("b", "D", null, label0, label1, 2);
            methodVisitor.visitLocalVariable("c", "Ljava/lang/String;", null, label0, label1, 4);
            methodVisitor.visitLocalVariable("d", "Ljava/lang/Float;", null, label0, label1, 5);
            methodVisitor.visitMaxs(4, 6);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "getRunnable", "()Ljava/lang/Object;", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(64, label0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitInvokeDynamicInsn(
                    "run",
                    "(Lcom/github/zszlly/agent/GeneratedClassA;)Ljava/lang/Runnable;",
                    new Handle(Opcodes.H_INVOKESTATIC,
                            "java/lang/invoke/LambdaMetafactory",
                            "metafactory",
                            "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;",
                            false),
                    new Object[]{
                            Type.getType("()V"),
                            new Handle(
                                    Opcodes.H_INVOKESPECIAL,
                                    "com/github/zszlly/agent/GeneratedClassA",
                                    "lambda$getRunnable$0",
                                    "()V",
                                    false),
                            Type.getType("()V")
                    });
            methodVisitor.visitVarInsn(ASTORE, 1);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(69, label1);
            methodVisitor.visitVarInsn(ALOAD, 1);
            methodVisitor.visitInsn(ARETURN);
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLocalVariable("this", "Lcom/github/zszlly/agent/GeneratedClassA;", null, label0, label2, 0);
            methodVisitor.visitLocalVariable("runnable", "Ljava/lang/Runnable;", null, label1, label2, 1);
            methodVisitor.visitMaxs(1, 2);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "listAdd", "(Ljava/lang/Object;)V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(73, label0);
            methodVisitor.visitTypeInsn(NEW, "java/util/LinkedList");
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/util/LinkedList", "<init>", "()V", false);
            methodVisitor.visitVarInsn(ASTORE, 2);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(74, label1);
            methodVisitor.visitVarInsn(ALOAD, 2);
            methodVisitor.visitVarInsn(ALOAD, 1);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/LinkedList", "add", "(Ljava/lang/Object;)Z", false);
            methodVisitor.visitInsn(POP);
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLineNumber(75, label2);
            methodVisitor.visitInsn(RETURN);
            Label label3 = new Label();
            methodVisitor.visitLabel(label3);
            methodVisitor.visitLocalVariable("this", "Lcom/github/zszlly/agent/GeneratedClassA;", null, label0, label3, 0);
            methodVisitor.visitLocalVariable("obj", "Ljava/lang/Object;", null, label0, label3, 1);
            methodVisitor.visitLocalVariable("list", "Ljava/util/LinkedList;", "Ljava/util/LinkedList<Ljava/lang/Object;>;", label1, label3, 2);
            methodVisitor.visitMaxs(2, 3);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "createRecord", "(Ljava/lang/reflect/Method;)V", null, new String[]{"java/lang/NoSuchMethodException"});
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(78, label0);
            methodVisitor.visitTypeInsn(NEW, "com/github/zszlly/model/MethodHolder");
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitVarInsn(ALOAD, 1);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "com/github/zszlly/model/MethodHolder", "<init>", "(Ljava/lang/reflect/Method;)V", false);
            methodVisitor.visitVarInsn(ASTORE, 2);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(79, label1);
            methodVisitor.visitTypeInsn(NEW, "java/util/LinkedList");
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/util/LinkedList", "<init>", "()V", false);
            methodVisitor.visitVarInsn(ASTORE, 3);
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLineNumber(80, label2);
            methodVisitor.visitTypeInsn(NEW, "com/github/zszlly/model/VoidArgument");
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "com/github/zszlly/model/VoidArgument", "<init>", "()V", false);
            methodVisitor.visitVarInsn(ASTORE, 4);
            Label label3 = new Label();
            methodVisitor.visitLabel(label3);
            methodVisitor.visitLineNumber(81, label3);
            methodVisitor.visitTypeInsn(NEW, "com/github/zszlly/model/Record");
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitVarInsn(ALOAD, 2);
            methodVisitor.visitVarInsn(ALOAD, 3);
            methodVisitor.visitVarInsn(ALOAD, 4);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "com/github/zszlly/model/Record", "<init>", "(Lcom/github/zszlly/model/MethodHolder;Ljava/util/List;Lcom/github/zszlly/model/Argument;)V", false);
            methodVisitor.visitVarInsn(ASTORE, 5);
            Label label4 = new Label();
            methodVisitor.visitLabel(label4);
            methodVisitor.visitLineNumber(82, label4);
            methodVisitor.visitInsn(RETURN);
            Label label5 = new Label();
            methodVisitor.visitLabel(label5);
            methodVisitor.visitLocalVariable("this", "Lcom/github/zszlly/agent/GeneratedClassA;", null, label0, label5, 0);
            methodVisitor.visitLocalVariable("method", "Ljava/lang/reflect/Method;", null, label0, label5, 1);
            methodVisitor.visitLocalVariable("holder", "Lcom/github/zszlly/model/MethodHolder;", null, label1, label5, 2);
            methodVisitor.visitLocalVariable("list", "Ljava/util/List;", "Ljava/util/List<Lcom/github/zszlly/model/Argument;>;", label2, label5, 3);
            methodVisitor.visitLocalVariable("returnArgument", "Lcom/github/zszlly/model/VoidArgument;", null, label3, label5, 4);
            methodVisitor.visitLocalVariable("record", "Lcom/github/zszlly/model/Record;", null, label4, label5, 5);
            methodVisitor.visitMaxs(5, 6);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "cast", "(Ljava/lang/Object;)V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(85, label0);
            methodVisitor.visitVarInsn(ALOAD, 1);
            methodVisitor.visitTypeInsn(CHECKCAST, "java/lang/Integer");
            methodVisitor.visitVarInsn(ASTORE, 2);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(86, label1);
            methodVisitor.visitInsn(RETURN);
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLocalVariable("this", "Lcom/github/zszlly/agent/GeneratedClassA;", null, label0, label2, 0);
            methodVisitor.visitLocalVariable("i", "Ljava/lang/Object;", null, label0, label2, 1);
            methodVisitor.visitLocalVariable("ii", "Ljava/lang/Integer;", null, label1, label2, 2);
            methodVisitor.visitMaxs(1, 3);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "readArgs", "([Ljava/lang/Object;)V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(89, label0);
            methodVisitor.visitVarInsn(ALOAD, 1);
            methodVisitor.visitInsn(ICONST_0);
            methodVisitor.visitInsn(AALOAD);
            methodVisitor.visitVarInsn(ASTORE, 2);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(90, label1);
            methodVisitor.visitVarInsn(ALOAD, 1);
            methodVisitor.visitInsn(ICONST_1);
            methodVisitor.visitInsn(AALOAD);
            methodVisitor.visitVarInsn(ASTORE, 3);
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLineNumber(91, label2);
            methodVisitor.visitInsn(RETURN);
            Label label3 = new Label();
            methodVisitor.visitLabel(label3);
            methodVisitor.visitLocalVariable("this", "Lcom/github/zszlly/agent/GeneratedClassA;", null, label0, label3, 0);
            methodVisitor.visitLocalVariable("args", "[Ljava/lang/Object;", null, label0, label3, 1);
            methodVisitor.visitLocalVariable("obj", "Ljava/lang/Object;", null, label1, label3, 2);
            methodVisitor.visitLocalVariable("obj2", "Ljava/lang/Object;", null, label2, label3, 3);
            methodVisitor.visitMaxs(2, 4);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "field", "()V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(94, label0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitTypeInsn(NEW, "com/github/zszlly/builder/CaseBuilder");
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "com/github/zszlly/builder/CaseBuilder", "<init>", "()V", false);
            methodVisitor.visitFieldInsn(PUTFIELD, "com/github/zszlly/agent/GeneratedClassA", "field", "Lcom/github/zszlly/builder/CaseBuilder;");
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(95, label1);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitFieldInsn(GETFIELD, "com/github/zszlly/agent/GeneratedClassA", "field", "Lcom/github/zszlly/builder/CaseBuilder;");
            methodVisitor.visitVarInsn(ASTORE, 1);
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLineNumber(96, label2);
            methodVisitor.visitInsn(RETURN);
            Label label3 = new Label();
            methodVisitor.visitLabel(label3);
            methodVisitor.visitLocalVariable("this", "Lcom/github/zszlly/agent/GeneratedClassA;", null, label0, label3, 0);
            methodVisitor.visitLocalVariable("var", "Lcom/github/zszlly/builder/CaseBuilder;", null, label2, label3, 1);
            methodVisitor.visitMaxs(3, 2);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "invokeInterface", "(Ljava/util/List;)V", "(Ljava/util/List<*>;)V", null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(99, label0);
            methodVisitor.visitVarInsn(ALOAD, 1);
            methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "size", "()I", true);
            methodVisitor.visitInsn(POP);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(100, label1);
            methodVisitor.visitInsn(RETURN);
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLocalVariable("this", "Lcom/github/zszlly/agent/GeneratedClassA;", null, label0, label2, 0);
            methodVisitor.visitLocalVariable("list", "Ljava/util/List;", "Ljava/util/List<*>;", label0, label2, 1);
            methodVisitor.visitMaxs(1, 2);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "ifAsm", "()I", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(104, label0);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitTypeInsn(INSTANCEOF, "com/github/zszlly/mark/NoTestMark");
            Label label1 = new Label();
            methodVisitor.visitJumpInsn(IFEQ, label1);
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLineNumber(105, label2);
            methodVisitor.visitInsn(ICONST_1);
            methodVisitor.visitInsn(IRETURN);
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(107, label1);
            methodVisitor.visitFrame(Opcodes.F_NEW, 1, new Object[]{"com/github/zszlly/agent/GeneratedClassA"}, 0, new Object[]{});
            methodVisitor.visitInsn(ICONST_M1);
            methodVisitor.visitInsn(IRETURN);
            Label label3 = new Label();
            methodVisitor.visitLabel(label3);
            methodVisitor.visitLocalVariable("this", "Lcom/github/zszlly/agent/GeneratedClassA;", null, label0, label3, 0);
            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PRIVATE | ACC_SYNTHETIC, "lambda$getRunnable$0", "()V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            methodVisitor.visitLabel(label0);
            methodVisitor.visitLineNumber(65, label0);
            methodVisitor.visitInsn(ICONST_0);
            methodVisitor.visitVarInsn(ISTORE, 1);
            Label label1 = new Label();
            methodVisitor.visitLabel(label1);
            methodVisitor.visitLineNumber(66, label1);
            methodVisitor.visitInsn(ICONST_0);
            methodVisitor.visitVarInsn(ISTORE, 2);
            Label label2 = new Label();
            methodVisitor.visitLabel(label2);
            methodVisitor.visitLineNumber(67, label2);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitTypeInsn(NEW, "com/github/zszlly/builder/CaseBuilder");
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "com/github/zszlly/builder/CaseBuilder", "<init>", "()V", false);
            methodVisitor.visitFieldInsn(PUTFIELD, "com/github/zszlly/agent/GeneratedClassA", "field", "Lcom/github/zszlly/builder/CaseBuilder;");
            Label label3 = new Label();
            methodVisitor.visitLabel(label3);
            methodVisitor.visitLineNumber(68, label3);
            methodVisitor.visitInsn(RETURN);
            Label label4 = new Label();
            methodVisitor.visitLabel(label4);
            methodVisitor.visitLocalVariable("this", "Lcom/github/zszlly/agent/GeneratedClassA;", null, label0, label4, 0);
            methodVisitor.visitLocalVariable("a", "I", null, label1, label4, 1);
            methodVisitor.visitLocalVariable("b", "I", null, label2, label4, 2);
            methodVisitor.visitMaxs(3, 3);
            methodVisitor.visitEnd();
        }
        classWriter.visitEnd();

        return classWriter.toByteArray();
    }

}

