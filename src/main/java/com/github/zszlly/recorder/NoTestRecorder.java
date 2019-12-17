package com.github.zszlly.recorder;

import com.github.zszlly.io.CaseSaver;
import com.github.zszlly.recorder.proxy.NoTestInstanceProxy;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class NoTestRecorder {

    private NoTestRecorder() {}

    public static <T> T record(Class<T> clazz, CaseSaver saver) {
        try {
            return record(clazz.getConstructor().newInstance(), saver);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T record(T instance, CaseSaver saver) {
        Class<?> clazz = instance.getClass();
        Arrays.stream(clazz.getDeclaredMethods())
                .forEach(NoTestInstanceProxy::setPermission);
        Enhancer e = new Enhancer();
        e.setSuperclass(clazz);
        NoTestInstanceProxy instanceProxy = new NoTestInstanceProxy(instance, saver);
        e.setCallback(instanceProxy);
        T proxiedInstance = (T) e.create();
        instanceProxy.setProxiedInstance(proxiedInstance);
        return proxiedInstance;
    }

}
