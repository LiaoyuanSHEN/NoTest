package com.github.zszlly.agent;

import com.github.zszlly.builder.CaseBuilder;
import com.github.zszlly.mark.NoTestMark;
import com.github.zszlly.model.Argument;
import com.github.zszlly.model.MethodHolder;
import com.github.zszlly.model.Record;
import com.github.zszlly.model.VoidArgument;
import com.github.zszlly.util.NoTestUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GeneratedClassA {

    private CaseBuilder field;

    public void boxing(boolean v1, char v2, byte v3, short v4, int v5, long v6, float v7, double v8) {
        Boolean.valueOf(v1);
        Character.valueOf(v2);
        Byte.valueOf(v3);
        Short.valueOf(v4);
        Integer.valueOf(v5);
        Long.valueOf(v6);
        Float.valueOf(v7);
        Double.valueOf(v8);
    }

    public void unboxing(Boolean v1, Character v2, Byte v3, Short v4, Integer v5, Long v6, Float v7, Double v8) {
        boolean vv1 = v1;
        char vv2 = v2;
        byte vv3 = v3;
        short vv4 = v4;
        int vv5 = v5;
        long vv6 = v6;
        float vv7 = v7;
        double vv8 = v8;
    }

    public void array(Integer[][] arr) {

    }

    public void invokeVirtual() {
        List<Object> list = new LinkedList<>();
        list.add(this);
    }

    public void getField() {
        System.out.println(NoTestUtils.VOID_ARGUMENT);
    }

    public void out() {
        System.out.println("123");
    }

    public double test(int a, double b, String c, Float d) {
        return a + b + Double.parseDouble(c) + d;
    }

    public Object getRunnable() {

        Runnable runnable = () -> {
            int a = 0;
            int b = 0;
            field = new CaseBuilder();
        };
        return runnable;
    }

    public void listAdd(Object obj) {
        LinkedList<Object> list = new LinkedList<>();
        list.add(obj);
    }

    public void createRecord(Method method) throws NoSuchMethodException {
        MethodHolder holder = new MethodHolder(method);
        List<Argument> list = new LinkedList<>();
        VoidArgument returnArgument = new VoidArgument();
        Record record = new Record(holder, list, returnArgument);
    }

    public void cast(Object i) {
        Integer ii = (Integer) i;
    }

    public void readArgs(Object[] args) {
        Object obj = args[0];
        Object obj2 = args[1];
    }

    public void field() {
        field = new CaseBuilder();
        CaseBuilder var = field;
    }

    public void invokeInterface(List<?> list) {
        list.size();
    }

    public int ifAsm() {
        if (this instanceof NoTestMark) {
            return 1;
        }
        return -1;
    }

}
