package com.github.zszlly.util;

import java.lang.reflect.Field;

public class FieldUtils {

    private FieldUtils() {

    }

    public static void setAccessible(Field field) {
        field.setAccessible(true);
    }

    public static Object getValue(Field field, Object obj) {
        setAccessible(field);
        try {
            return field.get(obj);
        } catch (IllegalAccessException e) {
            SneakyThrow.sneakyThrow(e);
        }
        // would never invoke.
        throw new IllegalStateException("This could not happen.");
    }

    public static void setValue(Object obj, String fieldName, Object fieldValue) {
        try {
            setValue(obj, obj.getClass().getDeclaredField(fieldName), fieldValue) ;
        } catch (NoSuchFieldException e) {
            SneakyThrow.sneakyThrow(e);
        }
    }

    public static void setValue(Object obj, Field field, Object fieldValue) {
        setAccessible(field);
        try {
            field.set(obj, fieldValue);
        } catch (IllegalAccessException e) {
            SneakyThrow.sneakyThrow(e);
        }
    }

}
