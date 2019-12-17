package com.github.zszlly.util;

import com.github.zszlly.annotation.NoTest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class NoTestMethodScanner {

    public static Collection<Method> scanMethods(Class<?> clazz) {
        return scanMethods(clazz, NoTest.class);
    }

    public static Collection<Method> scanMethods(Class<?> clazz, Class<? extends Annotation> annotation) {
        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(annotation))
                .collect(Collectors.toList());
    }

}
