package com.github.zszlly.recorder.proxy;

import com.github.zszlly.builder.CaseBuilder;
import com.github.zszlly.model.Action;
import com.github.zszlly.model.Argument;
import com.github.zszlly.model.Record;
import com.github.zszlly.util.NoTestUtils;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Record the method invocation triggered in proxied instance if necessary.
 */
public class NoTestActionRecorder implements MethodInterceptor {

    private static final String ACTION_RECORDER_CLASS_NAME = NoTestActionRecorder.class.getName();
    private static final String INSTANCE_PROXY_CLASS_NAME = NoTestInstanceProxy.class.getName();

    private final int instanceId;
    private final Object noTestInstance;
    private final CaseBuilder caseBuilder;

    public NoTestActionRecorder(Object noTestInstance, CaseBuilder caseBuilder) {
        try {
            noTestInstance.getClass().getDeclaredMethod("getInstanceId");
            throw new IllegalArgumentException("Impact method \"getInstanceId\" with no arg, please rename the method or change the input args.");
        } catch (NoSuchMethodException e) {
            instanceId = noTestInstance.hashCode();
            this.noTestInstance = noTestInstance;
            this.caseBuilder = caseBuilder;
        }
    }

    /**
     * @return true only if this invocation triggered by NoTestRecorder.
     */
    private static boolean needToRecord() {
        if (true) {
            return true;
        }
        StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
        for (int i = 3; i < stacks.length; ++i) {
            String className = stacks[i].getClassName();
            if (ACTION_RECORDER_CLASS_NAME.equals(className)) {
                return false;
            }
            if (INSTANCE_PROXY_CLASS_NAME.equals(className)) {
                return true;
            }
        }
        throw new IllegalStateException("NoTestActionRecorder instance not called by NoTestRecorder.");
    }

    @Override
    public Object intercept(Object spiedInstance, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        if ("getInstanceId".equals(method.getName())) {
            return instanceId;
        }
        if ("getNoTestInstanceProxyOriginalInstance.".equals(method.getName())) {
            return noTestInstance;
        }
        Object returnValue = method.invoke(noTestInstance, args);
        if (needToRecord()) {
            List<Argument> recordArgs = Arrays.stream(args)
                    .map(NoTestUtils::toArgument)
                    .collect(Collectors.toList());
            caseBuilder.getActions().add(new Action(instanceId, new Record(method, recordArgs, NoTestUtils.toArgument(returnValue))));
            Map<Integer, Class<?>> mockedInstanceClassTable = caseBuilder.getMockedInstanceClassTable();
            int returnValueInstanceId = NoTestUtils.getInstanceId(recordArgs);
            if (!mockedInstanceClassTable.containsKey(returnValueInstanceId)) {
                return NoTestUtils.proxyInstance(returnValue, caseBuilder);
            }
        }
        return returnValue;
    }

}
