package com.github.zszlly.recorder.proxy;

import com.github.zszlly.annotation.NoTest;
import com.github.zszlly.builder.CaseBuilder;
import com.github.zszlly.io.CaseSaver;
import com.github.zszlly.mark.ProxiedInstance;
import com.github.zszlly.model.Argument;
import com.github.zszlly.model.Record;
import com.github.zszlly.util.ClassUtils;
import com.github.zszlly.util.FieldUtils;
import com.github.zszlly.util.NoTestUtils;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * An instance proxy, will record this invocation and save it as Case.
 */
public class NoTestInstanceProxy implements MethodInterceptor {

    private final Object originalInstance;
    private final CaseSaver saver;
    private Object proxiedInstance;

    public NoTestInstanceProxy(Object originalInstance, CaseSaver saver) {
        this.originalInstance = originalInstance;
        this.saver = saver;
    }

    public static void setPermission(Method method) {
        method.setAccessible(true);
    }

    private static Object getOriginalObject(Object instance) {
        if (instance == null || ClassUtils.isPrimitive(instance)) {
            return instance;
        }
        if (!(instance instanceof ProxiedInstance)) {
            throw new IllegalArgumentException("Trying to get original instance from a non-proxied instance: " + instance);
        }
        return ((ProxiedInstance) instance).getNoTestInstanceProxyOriginalInstance();
    }

    private static void replaceFieldValue(Object instance, Field[] fields, Object[] values) {
        for (int i = 0; i < fields.length; i++) {
            FieldUtils.setValue(instance, fields[i], values[i]);
        }
    }

    // method entry
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        Field[] fields = originalInstance.getClass().getDeclaredFields();
        if (!method.isAnnotationPresent(NoTest.class)) {
            Object returnValue = method.invoke(originalInstance, args);
            // update proxied fields
            replaceFieldValue(proxiedInstance, fields, Arrays.stream(fields)
                    .map(field -> FieldUtils.getValue(field, originalInstance))
                    .toArray());
            return returnValue;
        }
        CaseBuilder caseBuilder = new CaseBuilder();
        Map<String, Argument> fieldTable = caseBuilder.getFieldTable();
        // proxy arguments
        Object[] proxiedArgs = Arrays.stream(args)
                .map(arg -> NoTestUtils.proxyInstance(arg, caseBuilder))
                .toArray();
        // proxy fields
        Object[] proxiedFieldValues = Arrays.stream(fields)
                .map(field -> NoTestUtils.proxyInstance(FieldUtils.getValue(field, proxiedInstance), caseBuilder))
                .toArray();
        replaceFieldValue(originalInstance, fields, proxiedFieldValues);
        for (int i = 0; i < fields.length; i++) {
            fieldTable.put(fields[i].getName(), NoTestUtils.toArgument(proxiedFieldValues[i]));
        }
        // invoke
        Object returnValue = method.invoke(originalInstance, proxiedArgs);
        List<Argument> arguments = NoTestUtils.toArgumentsList(proxiedArgs);
        caseBuilder.setRecord(new Record(method, arguments, NoTestUtils.toArgument(returnValue)));
        saver.addCase(caseBuilder.build());
        // revert fields
        replaceFieldValue(proxiedInstance, fields, Arrays.stream(proxiedFieldValues)
                .map(NoTestInstanceProxy::getOriginalObject)
                .toArray());
        return returnValue;
    }

    public void setProxiedInstance(Object proxiedInstance) {
        this.proxiedInstance = proxiedInstance;
    }
}
