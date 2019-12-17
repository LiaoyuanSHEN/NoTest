package com.github.zszlly.player.mock;

import com.github.zszlly.model.*;
import com.github.zszlly.util.ClassUtils;
import com.github.zszlly.util.FieldUtils;
import com.github.zszlly.util.NoTestUtils;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * NoTest case playback entry, read case and mock instances then run the test.
 */
public class NoTestCasePlayer implements Runnable {

    private Case c;
    private Map<Integer, NoTestInstanceMocker> mockerMap = new HashMap<>();

    private NoTestCasePlayer(Case c) {
        this.c = c;
    }

    public static void runCase(Case c) {
        NoTestCasePlayer casePlayer = new NoTestCasePlayer(c);
        casePlayer.run();
    }

    @Override
    public void run() {
        Method method = c.getRecord().getMethod();
        mockerMap.put(0, new NoTestInstanceMocker(0, mockerMap));
        prepareCase();
        Object returnValue = testMethod(proxyInstance(), method, convertArgs());
        mockerMap.values().forEach(NoTestInstanceMocker::checkInvocation);
        NoTestUtils.validInstance(c.getRecord().getReturnValue(), returnValue);
    }

    private void prepareCase() {
        c.getMockedInstanceClassTable()
                .forEach((id, clazz) -> {
                    NoTestInstanceMocker instanceMocker = NoTestInstanceMocker.mock(id, clazz, mockerMap);
                    mockerMap.put(id, instanceMocker);
                });
        c.getActions().forEach(action ->
                mockerMap.get(action.getInstanceId()).addRecord(action.getRecord()));
    }

    private Object[] convertArgs() {
        List<Argument> args = c.getRecord().getArgs();
        Object[] mockedArgs = new Object[args.size()];
        for (int i = 0; i < mockedArgs.length; i++) {
            mockedArgs[i] = mapInstance(args.get(i));
        }
        return mockedArgs;
    }

    private Object mapInstance(Argument arg) {
        if (arg == null || arg instanceof NullArgument || arg instanceof VoidArgument) {
            return null;
        }
        if (arg instanceof PrimitiveArgument) {
            return ((PrimitiveArgument) arg).getValueInstance();
        }
        if (arg instanceof MockedArgument) {
            return mockerMap.get(arg.getInstanceId()).getInstance();
        }
        if (arg instanceof ArrayArgument) {
            ArrayArgument arrArg = (ArrayArgument) arg;
            List<Argument> elements = arrArg.getElements();
            int len = elements.size();
            Object arr = Array.newInstance(ClassUtils.forName(arrArg.getElementTypeName()), len);
            for (int i = 0; i < len; ++i) {
                Array.set(arr, i, mapInstance(elements.get(i)));
            }
            return arr;
        }
        throw new IllegalArgumentException("Unsupported argument: " + arg.toString());
    }

    private Object proxyInstance() {
        try {
            // TODO: enhance this to support create instance without default constructor.
            Class<?> clazz = c.getRecord().getMethod().getDeclaringClass();
            Object instance = clazz.getConstructor().newInstance();
            Map<String, Argument> fieldTable = c.getFieldTable();
            Arrays.stream(clazz.getDeclaredFields())
                    .peek(field -> field.setAccessible(true))
                    .forEach(field -> FieldUtils.setValue(instance, field, mapInstance(fieldTable.get(field.getName()))));
            return instance;
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException | InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    private Object testMethod(Object instance, Method method, Object[] args) {
        try {
            method.setAccessible(true);
            return method.invoke(instance, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException(e);
        }
    }
}
