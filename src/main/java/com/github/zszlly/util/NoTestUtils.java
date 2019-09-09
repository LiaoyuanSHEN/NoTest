package com.github.zszlly.util;

import com.github.zszlly.builder.CaseBuilder;
import com.github.zszlly.exceptions.WrongArgumentsException;
import com.github.zszlly.mark.ProxiedInstance;
import com.github.zszlly.model.*;
import com.github.zszlly.recorder.proxy.NoTestActionRecorder;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NoTestUtils {

    private static final String LAMBDA_PREFIX = "$$Lambda$";
    public static final VoidArgument VOID_ARGUMENT = new VoidArgument();

    private NoTestUtils() {
    }

    public static boolean isLambda(Class<?> clazz) {
        return clazz.getSimpleName().contains(LAMBDA_PREFIX);
    }

    public static int getInstanceId(Object instance) {
        if (instance == null) {
            return 0;
        }
        if (instance instanceof ProxiedInstance) {
            return ((ProxiedInstance) instance).getInstanceId();
        }
        return instance.hashCode();
    }

    public static List<Argument> toArgumentsList(Object[] arguments) {
        return Arrays.stream(arguments)
                .map(NoTestUtils::toArgument)
                .collect(Collectors.toList());
    }

    public static Argument toArgument(Object instance) {
        if (instance == null) {
            return new NullArgument();
        }
        Class<?> clazz = instance.getClass();
        if (clazz.isArray()) {
            int len = Array.getLength(instance);

        }
        if (ClassUtils.isPrimitive(clazz)) {
            return new PrimitiveArgument(clazz, instance.toString());
        }
        if (instance instanceof ProxiedInstance) {
            return new MockedArgument(getInstanceId(instance));
        }
        return new GeneratedByMethodArgument(clazz);
    }

    public static void validInstance(Argument wanted, Object actual) {
        if (wanted instanceof VoidArgument && actual == null) {
            return;
        }
        if (wanted.getInstanceId() == 0) {
            // want null as argument
            if (actual == null) {
                return;
            }
            throw new WrongArgumentsException("Invocation want NULL but inputted: " + actual.getClass());
        }
        if (wanted instanceof PrimitiveArgument) {
            Object primitiveValue = ((PrimitiveArgument) wanted).getValueInstance();
            if (primitiveValue.equals(actual)) {
                return;
            }
            throw new WrongArgumentsException("Invocation want primitive value: " + primitiveValue + " but inputted: " + actual);
        }
        if (wanted instanceof GeneratedByMethodArgument) {
            // this argument is created by tested method, no need to valid.
            GeneratedByMethodArgument generatedByMethodArgument = (GeneratedByMethodArgument) wanted;
            if (generatedByMethodArgument.getObjectClass() == actual.getClass()) {
                return;
            }
            throw new WrongArgumentsException("Invocation want class: " + generatedByMethodArgument.getObjectClass() + " but inputted: " + actual.getClass());
        }
        if (wanted instanceof MockedArgument) {
            if (wanted.getInstanceId() == ((ProxiedInstance) actual).getInstanceId()) {
                return;
            }
            throw new WrongArgumentsException("Invocation want instanceId: " + wanted.getInstanceId() + " but inputted: " + ((ProxiedInstance) actual).getInstanceId());
        }
        throw new WrongArgumentsException("Unsupported mock type: " + wanted.getClass().getName());
    }

    public static Object proxyInstance(Object instance, CaseBuilder caseBuilder) {
        if (instance == null) {
            return null;
        }
        if (instance instanceof ProxiedInstance || ClassUtils.isPrimitive(instance)) {
            return instance;
        }
        Map<Integer, Class<?>> mockedInstanceClassTable = caseBuilder.getMockedInstanceClassTable();
        Class<?> clazz = instance.getClass();
        Enhancer e = new Enhancer();
        if (NoTestUtils.isLambda(clazz)) {
            Class<?> interfaceClass = clazz.getInterfaces()[0];
            mockedInstanceClassTable.put(instance.hashCode(), interfaceClass);
            e.setInterfaces(new Class[]{interfaceClass, ProxiedInstance.class});
        } else {
            mockedInstanceClassTable.put(instance.hashCode(), clazz);
            e.setSuperclass(clazz);
            e.setInterfaces(new Class[]{ProxiedInstance.class});
        }
        e.setCallback(new NoTestActionRecorder(instance, caseBuilder));
        return e.create();
    }

    // CaseBuilder ?
    public static Argument arrayElementToArgument(Object arr, int index) {
        Class<?> clazz = arr.getClass();
        if (clazz == boolean[].class) {
            return new PrimitiveArgument("boolean", Boolean.toString(Array.getBoolean(arr, index)));
        } else if (clazz == char[].class) {
            return new PrimitiveArgument("char", Character.toString(Array.getChar(arr, index)));
        } else if (clazz == short[].class) {
            return new PrimitiveArgument("short", Short.toString(Array.getShort(arr, index)));
        } else if (clazz == int[].class) {
            return new PrimitiveArgument("int", Integer.toString(Array.getInt(arr, index)));
        } else if (clazz == long[].class) {
            return new PrimitiveArgument("long", Long.toString(Array.getLong(arr, index)));
        } else if (clazz == float[].class) {
            return new PrimitiveArgument("float", Float.toString(Array.getFloat(arr, index)));
        } else if (clazz == double[].class) {
            return new PrimitiveArgument("double", Double.toString(Array.getDouble(arr, index)));
        }
        // TODO
        return null;
    }

}
