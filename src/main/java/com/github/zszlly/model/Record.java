package com.github.zszlly.model;

import com.github.zszlly.mock.MockedClassMark;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

public class Record {

    private Method method;
    private MockedClassMark[] args;
    private MockedClassMark returnValue;

    public Record(Method method, MockedClassMark[] args, MockedClassMark returnValue) {
        this.method = method;
        this.args = args;
        this.returnValue = returnValue;
    }

    public Method getMethod() {
        return method;
    }

    public MockedClassMark[] getArgs() {
        return args;
    }

    public MockedClassMark getReturnValue() {
        return returnValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return Objects.equals(method, record.method) &&
                Arrays.equals(args, record.args) &&
                Objects.equals(returnValue, record.returnValue);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(method, returnValue);
        result = 31 * result + Arrays.hashCode(args);
        return result;
    }
}
