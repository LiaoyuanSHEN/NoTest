package com.github.zszlly.player.mock;

import com.github.zszlly.mark.ProxiedInstance;
import com.github.zszlly.model.Record;
import com.github.zszlly.util.ClassUtils;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Instance Mocker, will intercept every invocation and redirect to corresponding MethodMocker.
 */
public class NoTestInstanceMocker implements MethodInterceptor {

    private final int instanceId;
    private final Map<Integer, NoTestInstanceMocker> mockedObjectMap;
    private Map<Method, NoTestMethodMocker> methodMockerMap = new HashMap<>();
    private Object instance = null;

    NoTestInstanceMocker(int instanceId, Map<Integer, NoTestInstanceMocker> mockedObjectMap) {
        this.instanceId = instanceId;
        this.mockedObjectMap = mockedObjectMap;
    }

    public static NoTestInstanceMocker mock(Integer id, Class<?> tClass, Map<Integer, NoTestInstanceMocker> mockedObjectMap) {
        if (ClassUtils.isPrimitive(tClass)) {
            throw new IllegalArgumentException("Unsupported mocked class: " + tClass);
        }
        NoTestInstanceMocker mocker = new NoTestInstanceMocker(id, mockedObjectMap);
        Enhancer e = new Enhancer();
        if (tClass.isInterface()) {
            e.setInterfaces(new Class[]{tClass, ProxiedInstance.class});
        } else {
            e.setSuperclass(tClass);
            e.setInterfaces(new Class[]{ProxiedInstance.class});
        }
        e.setCallback(mocker);
        mocker.setInstance(e.create());
        return mocker;
    }

    public void addRecord(Record mockedRecord) {
        methodMockerMap.computeIfAbsent(mockedRecord.getMethod(), method -> new NoTestMethodMocker(method, mockedObjectMap)).addRecord(mockedRecord);
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) {
        if ("getInstanceId".equals(method.getName())) {
            return instanceId;
        }
        return methodMockerMap.get(method).invoke(Arrays.asList(args));
    }

    public void checkInvocation() {
        methodMockerMap.values().forEach(NoTestMethodMocker::checkInvocationTimes);
    }

    public int getInstanceId() {
        return instanceId;
    }

    public Object getInstance() {
        return instance;
    }

    private void setInstance(Object instance) {
        this.instance = instance;
    }
}
