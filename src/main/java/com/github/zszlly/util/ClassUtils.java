package com.github.zszlly.util;

import org.objectweb.asm.Type;

import java.lang.reflect.Method;
import java.util.*;

public class ClassUtils {

    private static final Set<Class<?>> WRAPPED_PRIMITIVE_CLASSES = new HashSet<>();
    private static final Set<Class<?>> UNWRAPPED_PRIMITIVE_CLASSES = new HashSet<>();
    private static final Map<String, Class<?>> UNWRAPPED_PRIMITIVE_CLASSES_NAME_MAP = new HashMap<>();
    private static final Map<Class<?>, Class<?>> PRIMITIVE_WRAPPER_MAP = new HashMap<>();

    static {
        UNWRAPPED_PRIMITIVE_CLASSES.add(boolean.class);
        UNWRAPPED_PRIMITIVE_CLASSES.add(char.class);
        UNWRAPPED_PRIMITIVE_CLASSES.add(byte.class);
        UNWRAPPED_PRIMITIVE_CLASSES.add(short.class);
        UNWRAPPED_PRIMITIVE_CLASSES.add(int.class);
        UNWRAPPED_PRIMITIVE_CLASSES.add(long.class);
        UNWRAPPED_PRIMITIVE_CLASSES.add(float.class);
        UNWRAPPED_PRIMITIVE_CLASSES.add(double.class);
        UNWRAPPED_PRIMITIVE_CLASSES.add(void.class);

        WRAPPED_PRIMITIVE_CLASSES.add(Boolean.class);
        WRAPPED_PRIMITIVE_CLASSES.add(Character.class);
        WRAPPED_PRIMITIVE_CLASSES.add(Byte.class);
        WRAPPED_PRIMITIVE_CLASSES.add(Short.class);
        WRAPPED_PRIMITIVE_CLASSES.add(Integer.class);
        WRAPPED_PRIMITIVE_CLASSES.add(Long.class);
        WRAPPED_PRIMITIVE_CLASSES.add(Float.class);
        WRAPPED_PRIMITIVE_CLASSES.add(Double.class);
        WRAPPED_PRIMITIVE_CLASSES.add(Void.class);

        WRAPPED_PRIMITIVE_CLASSES.add(String.class);

        PRIMITIVE_WRAPPER_MAP.put(boolean.class, Boolean.class);
        PRIMITIVE_WRAPPER_MAP.put(char.class, Character.class);
        PRIMITIVE_WRAPPER_MAP.put(byte.class, Byte.class);
        PRIMITIVE_WRAPPER_MAP.put(short.class, Short.class);
        PRIMITIVE_WRAPPER_MAP.put(int.class, Integer.class);
        PRIMITIVE_WRAPPER_MAP.put(long.class, Long.class);
        PRIMITIVE_WRAPPER_MAP.put(float.class, Float.class);
        PRIMITIVE_WRAPPER_MAP.put(double.class, Double.class);
        PRIMITIVE_WRAPPER_MAP.put(void.class, Void.class);

        UNWRAPPED_PRIMITIVE_CLASSES_NAME_MAP.put(boolean.class.getName(), boolean.class);
        UNWRAPPED_PRIMITIVE_CLASSES_NAME_MAP.put(char.class.getName(), char.class);
        UNWRAPPED_PRIMITIVE_CLASSES_NAME_MAP.put(byte.class.getName(), byte.class);
        UNWRAPPED_PRIMITIVE_CLASSES_NAME_MAP.put(short.class.getName(), short.class);
        UNWRAPPED_PRIMITIVE_CLASSES_NAME_MAP.put(int.class.getName(), int.class);
        UNWRAPPED_PRIMITIVE_CLASSES_NAME_MAP.put(long.class.getName(), long.class);
        UNWRAPPED_PRIMITIVE_CLASSES_NAME_MAP.put(float.class.getName(), float.class);
        UNWRAPPED_PRIMITIVE_CLASSES_NAME_MAP.put(double.class.getName(), double.class);
        UNWRAPPED_PRIMITIVE_CLASSES_NAME_MAP.put(void.class.getName(), void.class);
    }

    private ClassUtils() {
    }

    public static Class<?> toWrappedPrimitiveClass(Class<?> clazz) {
        if (!isUnwrappedPrimitiveClass(clazz)) {
            throw new IllegalArgumentException("Unsupported unwrapped object: " + clazz);
        }
        return PRIMITIVE_WRAPPER_MAP.get(clazz);
    }

    public static boolean isPrimitive(Object instance) {
        return isPrimitive(instance.getClass());
    }

    public static boolean isPrimitive(Class<?> clazz) {
        return isUnwrappedPrimitiveClass(clazz) || isWrappedPrimitiveClass(clazz) || String.class == clazz;
    }

    public static boolean isUnwrappedPrimitiveClass(Class<?> clazz) {
        return UNWRAPPED_PRIMITIVE_CLASSES.contains(clazz);
    }

    public static boolean isWrappedPrimitiveClass(Class<?> clazz) {
        return WRAPPED_PRIMITIVE_CLASSES.contains(clazz);
    }

    public static Class<?> forName(String className) {
        if (UNWRAPPED_PRIMITIVE_CLASSES_NAME_MAP.containsKey(className)) {
            return UNWRAPPED_PRIMITIVE_CLASSES_NAME_MAP.get(className);
        }
        try {
            if (className.endsWith("[]")) {
                switch (className) {
                    case "boolean[]":
                        return boolean[].class;
                    case "char[]":
                        return char[].class;
                    case "byte[]":
                        return byte[].class;
                    case "short[]":
                        return short[].class;
                    case "int[]":
                        return int[].class;
                    case "float[]":
                        return float[].class;
                    case "long[]":
                        return long[].class;
                    case "double[]":
                        return double[].class;
                    default:
                        className = "[L" + className.replace("[]", "") + ";";
                }
            }
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            SneakyThrow.sneakyThrow(e);
        }
        throw new IllegalArgumentException("This will not happen!");
    }

    public static Method getMethod(Class<?> clazz, String methodName, List<String> argTypes) {
        try {
            return clazz.getDeclaredMethod(methodName, argTypes.stream()
                    .map(ClassUtils::forName)
                    .toArray(Class[]::new));
        } catch (NoSuchMethodException e) {
            SneakyThrow.sneakyThrow(e);
        }
        return null;
    }

    public static Class<?> forType(Type type) {
        return forName(type.getClassName());
    }

}
