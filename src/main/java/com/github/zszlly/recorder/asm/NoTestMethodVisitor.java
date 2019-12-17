package com.github.zszlly.recorder.asm;

import com.github.zszlly.builder.CaseBuilder;
import com.github.zszlly.util.ClassUtils;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.LocalVariablesSorter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.objectweb.asm.Opcodes.*;

public class NoTestMethodVisitor extends LocalVariablesSorter {

    private final MethodDescription methodDescription;
    private final List<FieldDescription> fieldDescriptions;
    private final boolean isStatic;
    private int caseBuilder;
    private int argumentsArray;

    public NoTestMethodVisitor(int api, int access, String desc, MethodVisitor mv, MethodDescription methodDescription, List<FieldDescription> fieldDescriptions, boolean isStatic) {
        super(api, access, desc, mv);
        this.methodDescription = methodDescription;
        this.fieldDescriptions = fieldDescriptions;
        this.isStatic = isStatic;
    }

    private static int localVarLength(Class<?> type) {
        if (type == boolean.class || type == char.class || type == byte.class || type == short.class || type == int.class || type == float.class) {
            return 1;
        } else if (type == long.class || type == double.class) {
            return 2;
        }
        return 1;
    }

    @Override
    public void visitCode() {
        super.visitCode();
        List<Class<?>> argumentsClass = Arrays.stream(methodDescription.getArgumentsType())
                .map(ClassUtils::forType)
                .collect(Collectors.toList());
        int argLen = argumentsClass.size();
        // create caseBuilder and save as "caseBuilder"
        mv.visitTypeInsn(NEW, "com/github/zszlly/builder/CaseBuilder");
        mv.visitInsn(DUP);
        mv.visitMethodInsn(INVOKESPECIAL, "com/github/zszlly/builder/CaseBuilder", "<init>", "()V", false);
        caseBuilder = newLocal(Type.getType(CaseBuilder.class));
        mv.visitVarInsn(ASTORE, caseBuilder);
        // spy fields
        if (!isStatic) {
            mv.visitVarInsn(ALOAD, caseBuilder);
            mv.visitMethodInsn(INVOKEVIRTUAL, "com/github/zszlly/builder/CaseBuilder", "getFieldTable", "()Ljava/util/Map;", false);
            fieldDescriptions
                    .forEach(fieldDescription -> {
                        String owner = fieldDescription.getOwner();
                        String name = fieldDescription.getName();
                        String typeDescriptor = fieldDescription.getTypeDescriptor();
                        mv.visitInsn(DUP);
                        mv.visitLdcInsn(name);
                        mv.visitVarInsn(ALOAD, 0);
                        mv.visitInsn(DUP);
                        mv.visitInsn(DUP);
                        mv.visitFieldInsn(GETFIELD, owner, name, typeDescriptor);
                        Class<?> fieldClass = ClassUtils.forType(Type.getType(typeDescriptor));
                        if (fieldClass.isPrimitive()) {
                            boxing(fieldClass);
                        }
                        mv.visitVarInsn(ALOAD, caseBuilder);
                        mv.visitMethodInsn(INVOKESTATIC, "com/github/zszlly/util/NoTestUtils", "proxyInstance", "(Ljava/lang/Object;Lcom/github/zszlly/builder/CaseBuilder;)Ljava/lang/Object;", false);
                        if (fieldClass.isPrimitive()) {
                            unboxing(fieldClass);
                        }
                        mv.visitFieldInsn(PUTFIELD, owner, name, typeDescriptor);
                        mv.visitFieldInsn(GETFIELD, owner, name, typeDescriptor);
                        if (fieldClass.isPrimitive()) {
                            boxing(fieldClass);
                        }
                        mv.visitMethodInsn(INVOKESTATIC, "com/github/zszlly/util/NoTestUtils", "toArgument", "(Ljava/lang/Object;)Lcom/github/zszlly/model/Argument;", false);
                        mv.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", true);
                        mv.visitInsn(POP);
                    });
            mv.visitInsn(POP);
        }
        // create arguments array
        mv.visitIntInsn(BIPUSH, argLen);
        mv.visitTypeInsn(ANEWARRAY, "java/lang/Object");
        int localVarIndex = isStatic ? 0 : 1;
        for (int i = 0; i < argLen; ++i) {
            Class<?> clazz = argumentsClass.get(i);
            mv.visitInsn(DUP);
            mv.visitIntInsn(BIPUSH, i);
            if (clazz.isPrimitive()) {
                loadAndBoxing(clazz, localVarIndex);
            } else {
                mv.visitVarInsn(ALOAD, localVarIndex);
            }
            // loading CaseBuilder
            mv.visitVarInsn(ALOAD, caseBuilder);
            mv.visitMethodInsn(INVOKESTATIC, "com/github/zszlly/util/NoTestUtils", "proxyInstance", "(Ljava/lang/Object;Lcom/github/zszlly/builder/CaseBuilder;)Ljava/lang/Object;", false);

            // replace the original argument with spied one
            unboxingAndReplaceArgument(clazz, localVarIndex);

            mv.visitInsn(AASTORE);
            localVarIndex += localVarLength(clazz);
        }
        // save this arguments array
        argumentsArray = newLocal(Type.getType(Object[].class));
        mv.visitVarInsn(ASTORE, argumentsArray);
    }

    @Override
    public void visitInsn(int opcode) {
        if ((opcode >= IRETURN && opcode <= RETURN) || opcode == ATHROW) {
            Class<?> returnTypeClass = ClassUtils.forType(methodDescription.getReturnType());
            int rawReturnValue;
            switch (opcode) {
                // record return value
                case IRETURN:
                    rawReturnValue = newLocal(Type.INT_TYPE);
                    mv.visitInsn(DUP);
                    mv.visitVarInsn(ISTORE, rawReturnValue);
                    saveCase();
                    mv.visitVarInsn(ILOAD, rawReturnValue);
                    boxing(returnTypeClass);
                    mv.visitMethodInsn(INVOKESTATIC, "com/github/zszlly/util/NoTestUtils", "toArgument", "(Ljava/lang/Object;)Lcom/github/zszlly/model/Argument;", false);
                    mv.visitMethodInsn(INVOKESPECIAL, "com/github/zszlly/model/Record", "<init>", "(Lcom/github/zszlly/model/MethodHolder;Ljava/util/List;Lcom/github/zszlly/model/Argument;)V", false);
                    mv.visitMethodInsn(INVOKEVIRTUAL, "com/github/zszlly/builder/CaseBuilder", "setRecord", "(Lcom/github/zszlly/model/Record;)V", false);
                    mv.visitMethodInsn(INVOKEVIRTUAL, "com/github/zszlly/builder/CaseBuilder", "build", "()Lcom/github/zszlly/model/Case;", false);
                    mv.visitMethodInsn(INVOKESTATIC, "com/github/zszlly/recorder/asm/ASMCaseSaver", "saveCase", "(Lcom/github/zszlly/model/Case;)V", false);
                    mv.visitVarInsn(ILOAD, rawReturnValue);
                    break;
                case FRETURN:
                    rawReturnValue = newLocal(Type.FLOAT_TYPE);
                    mv.visitInsn(DUP);
                    mv.visitVarInsn(FSTORE, rawReturnValue);
                    saveCase();
                    mv.visitVarInsn(FLOAD, rawReturnValue);
                    boxing(returnTypeClass);
                    mv.visitMethodInsn(INVOKESTATIC, "com/github/zszlly/util/NoTestUtils", "toArgument", "(Ljava/lang/Object;)Lcom/github/zszlly/model/Argument;", false);
                    mv.visitMethodInsn(INVOKESPECIAL, "com/github/zszlly/model/Record", "<init>", "(Lcom/github/zszlly/model/MethodHolder;Ljava/util/List;Lcom/github/zszlly/model/Argument;)V", false);
                    mv.visitMethodInsn(INVOKEVIRTUAL, "com/github/zszlly/builder/CaseBuilder", "setRecord", "(Lcom/github/zszlly/model/Record;)V", false);
                    mv.visitMethodInsn(INVOKEVIRTUAL, "com/github/zszlly/builder/CaseBuilder", "build", "()Lcom/github/zszlly/model/Case;", false);
                    mv.visitMethodInsn(INVOKESTATIC, "com/github/zszlly/recorder/asm/ASMCaseSaver", "saveCase", "(Lcom/github/zszlly/model/Case;)V", false);
                    mv.visitVarInsn(FLOAD, rawReturnValue);
                    break;
                case LRETURN:
                    rawReturnValue = newLocal(Type.LONG_TYPE);
                    mv.visitInsn(DUP2);
                    mv.visitVarInsn(LSTORE, rawReturnValue);
                    saveCase();
                    mv.visitVarInsn(LLOAD, rawReturnValue);
                    boxing(returnTypeClass);
                    mv.visitMethodInsn(INVOKESTATIC, "com/github/zszlly/util/NoTestUtils", "toArgument", "(Ljava/lang/Object;)Lcom/github/zszlly/model/Argument;", false);
                    mv.visitMethodInsn(INVOKESPECIAL, "com/github/zszlly/model/Record", "<init>", "(Lcom/github/zszlly/model/MethodHolder;Ljava/util/List;Lcom/github/zszlly/model/Argument;)V", false);
                    mv.visitMethodInsn(INVOKEVIRTUAL, "com/github/zszlly/builder/CaseBuilder", "setRecord", "(Lcom/github/zszlly/model/Record;)V", false);
                    mv.visitMethodInsn(INVOKEVIRTUAL, "com/github/zszlly/builder/CaseBuilder", "build", "()Lcom/github/zszlly/model/Case;", false);
                    mv.visitMethodInsn(INVOKESTATIC, "com/github/zszlly/recorder/asm/ASMCaseSaver", "saveCase", "(Lcom/github/zszlly/model/Case;)V", false);
                    mv.visitVarInsn(LLOAD, rawReturnValue);
                    break;
                case DRETURN:
                    rawReturnValue = newLocal(Type.DOUBLE_TYPE);
                    mv.visitInsn(DUP2);
                    mv.visitVarInsn(DSTORE, rawReturnValue);
                    saveCase();
                    mv.visitVarInsn(DLOAD, rawReturnValue);
                    boxing(returnTypeClass);
                    mv.visitMethodInsn(INVOKESTATIC, "com/github/zszlly/util/NoTestUtils", "toArgument", "(Ljava/lang/Object;)Lcom/github/zszlly/model/Argument;", false);
                    mv.visitMethodInsn(INVOKESPECIAL, "com/github/zszlly/model/Record", "<init>", "(Lcom/github/zszlly/model/MethodHolder;Ljava/util/List;Lcom/github/zszlly/model/Argument;)V", false);
                    mv.visitMethodInsn(INVOKEVIRTUAL, "com/github/zszlly/builder/CaseBuilder", "setRecord", "(Lcom/github/zszlly/model/Record;)V", false);
                    mv.visitMethodInsn(INVOKEVIRTUAL, "com/github/zszlly/builder/CaseBuilder", "build", "()Lcom/github/zszlly/model/Case;", false);
                    mv.visitMethodInsn(INVOKESTATIC, "com/github/zszlly/recorder/asm/ASMCaseSaver", "saveCase", "(Lcom/github/zszlly/model/Case;)V", false);
                    mv.visitVarInsn(DLOAD, rawReturnValue);
                    break;
                case ARETURN:
                    rawReturnValue = newLocal(Type.getType(returnTypeClass));
                    mv.visitInsn(DUP);
                    mv.visitVarInsn(ASTORE, rawReturnValue);
                    saveCase();
                    mv.visitVarInsn(ALOAD, rawReturnValue);
                    mv.visitMethodInsn(INVOKESTATIC, "com/github/zszlly/util/NoTestUtils", "toArgument", "(Ljava/lang/Object;)Lcom/github/zszlly/model/Argument;", false);
                    mv.visitMethodInsn(INVOKESPECIAL, "com/github/zszlly/model/Record", "<init>", "(Lcom/github/zszlly/model/MethodHolder;Ljava/util/List;Lcom/github/zszlly/model/Argument;)V", false);
                    mv.visitMethodInsn(INVOKEVIRTUAL, "com/github/zszlly/builder/CaseBuilder", "setRecord", "(Lcom/github/zszlly/model/Record;)V", false);
                    mv.visitMethodInsn(INVOKEVIRTUAL, "com/github/zszlly/builder/CaseBuilder", "build", "()Lcom/github/zszlly/model/Case;", false);
                    mv.visitMethodInsn(INVOKESTATIC, "com/github/zszlly/recorder/asm/ASMCaseSaver", "saveCase", "(Lcom/github/zszlly/model/Case;)V", false);
                    mv.visitVarInsn(ALOAD, rawReturnValue);
                    break;
                case RETURN:
                    saveCase();
                    mv.visitTypeInsn(NEW, "com/github/zszlly/model/VoidArgument");
                    mv.visitInsn(DUP);
                    mv.visitMethodInsn(INVOKESPECIAL, "com/github/zszlly/model/VoidArgument", "<init>", "()V", false);
//                    mv.visitTypeInsn(CHECKCAST, "com/github/zszlly/model/Argument");
                    mv.visitMethodInsn(INVOKESPECIAL, "com/github/zszlly/model/Record", "<init>", "(Lcom/github/zszlly/model/MethodHolder;Ljava/util/List;Lcom/github/zszlly/model/Argument;)V", false);
                    mv.visitMethodInsn(INVOKEVIRTUAL, "com/github/zszlly/builder/CaseBuilder", "setRecord", "(Lcom/github/zszlly/model/Record;)V", false);
                    mv.visitMethodInsn(INVOKEVIRTUAL, "com/github/zszlly/builder/CaseBuilder", "build", "()Lcom/github/zszlly/model/Case;", false);
                    mv.visitMethodInsn(INVOKESTATIC, "com/github/zszlly/recorder/asm/ASMCaseSaver", "saveCase", "(Lcom/github/zszlly/model/Case;)V", false);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported opcode: " + opcode);
            }
        }
        super.visitInsn(opcode);
    }

    private void saveCase() {
        mv.visitVarInsn(ALOAD, caseBuilder);
        mv.visitInsn(DUP);
        // alloc memory for record
        mv.visitTypeInsn(NEW, "com/github/zszlly/model/Record");
        mv.visitInsn(DUP);
        // alloc memory for methodHolder
        mv.visitTypeInsn(NEW, "com/github/zszlly/model/MethodHolder");
        mv.visitInsn(DUP);
        // put declaringClass as argument for methodHolder
        mv.visitLdcInsn(methodDescription.getOwner().replace('/', '.'));
        // put methodName as argument for methodHolder
        mv.visitLdcInsn(methodDescription.getName());
        // alloc memory of linkedlist of argument argumentTypes for methodHolder
        mv.visitTypeInsn(NEW, "java/util/LinkedList");
        mv.visitInsn(DUP);
        mv.visitMethodInsn(INVOKESPECIAL, "java/util/LinkedList", "<init>", "()V", false);
        Arrays.stream(methodDescription.getArgumentsType())
                .forEach(argumentType -> {
                    // put this method argument type into the linkedlist
                    mv.visitInsn(DUP);
                    mv.visitLdcInsn(argumentType.getClassName());
                    mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/LinkedList", "add", "(Ljava/lang/Object;)Z", false);
                    mv.visitInsn(POP);
                });
        // invoke methodHolder construct function
        mv.visitMethodInsn(INVOKESPECIAL, "com/github/zszlly/model/MethodHolder", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", false);
        mv.visitVarInsn(ALOAD, argumentsArray);
        // convert proxied args list to Argument
        mv.visitMethodInsn(INVOKESTATIC, "com/github/zszlly/util/NoTestUtils", "toArgumentsList", "([Ljava/lang/Object;)Ljava/util/List;", false);
    }

    private void unboxingAndReplaceArgument(Class<?> clazz, int index) {
        mv.visitInsn(DUP);
        unboxing(clazz);
        if (clazz == boolean.class || clazz == char.class || clazz == byte.class || clazz == short.class || clazz == int.class) {
            mv.visitVarInsn(ISTORE, index);
        } else if (clazz == long.class) {
            mv.visitVarInsn(LSTORE, index);
        } else if (clazz == float.class) {
            mv.visitVarInsn(FSTORE, index);
        } else if (clazz == double.class) {
            mv.visitVarInsn(DSTORE, index);
        } else {
            mv.visitVarInsn(ASTORE, index);
        }
    }

    private void loadAndBoxing(Class<?> clazz, int index) {
        if (clazz == boolean.class || clazz == char.class || clazz == byte.class || clazz == short.class || clazz == int.class) {
            mv.visitVarInsn(ILOAD, index);
        } else if (clazz == long.class) {
            mv.visitVarInsn(LLOAD, index);
        } else if (clazz == float.class) {
            mv.visitVarInsn(FLOAD, index);
        } else if (clazz == double.class) {
            mv.visitVarInsn(DLOAD, index);
        }
        boxing(clazz);
    }

    private void boxing(Class<?> clazz) {
        if (clazz == boolean.class) {
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;", false);
        } else if (clazz == char.class) {
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;", false);
        } else if (clazz == byte.class) {
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;", false);
        } else if (clazz == short.class) {
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;", false);
        } else if (clazz == int.class) {
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
        } else if (clazz == long.class) {
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Long", "valueOf", "(L)Ljava/lang/Long;", false);
        } else if (clazz == float.class) {
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;", false);
        } else if (clazz == double.class) {
            mv.visitMethodInsn(INVOKESTATIC, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;", false);
        }
    }

    private void unboxing(Class<?> clazz) {
        if (clazz == boolean.class) {
            mv.visitTypeInsn(CHECKCAST, "java/lang/Boolean");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Boolean", "booleanValue", "()Z", false);
        } else if (clazz == char.class) {
            mv.visitTypeInsn(CHECKCAST, "java/lang/Character");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Character", "charValue", "()C", false);
        } else if (clazz == byte.class) {
            mv.visitTypeInsn(CHECKCAST, "java/lang/Byte");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Byte", "byteValue", "()B", false);
        } else if (clazz == short.class) {
            mv.visitTypeInsn(CHECKCAST, "java/lang/Short");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Short", "shortValue", "()S", false);
        } else if (clazz == int.class) {
            mv.visitTypeInsn(CHECKCAST, "java/lang/Integer");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Integer", "intValue", "()I", false);
        } else if (clazz == long.class) {
            mv.visitTypeInsn(CHECKCAST, "java/lang/Long");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Long", "longValue", "()J", false);
        } else if (clazz == float.class) {
            mv.visitTypeInsn(CHECKCAST, "java/lang/Float");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Float", "floatValue", "()F", false);
        } else if (clazz == double.class) {
            mv.visitTypeInsn(CHECKCAST, "java/lang/Double");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Double", "doubleValue", "()D", false);
        } else if (clazz.isArray()) {
            mv.visitTypeInsn(CHECKCAST, Type.getType(clazz).getInternalName());
        }
    }

    private void printMsg(String msg) {
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn(msg);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    }


}
