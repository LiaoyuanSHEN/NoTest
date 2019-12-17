package com.github.zszlly.util;

import org.objectweb.asm.Type;

import java.lang.reflect.Method;

public class MethodUtils {

    public static String toNoTestMethodDescription(Method method) {
        return Type.getDescriptor(method.getDeclaringClass()) +
                "." +
                method.getName() +
                Type.getMethodDescriptor(method);
    }

}
