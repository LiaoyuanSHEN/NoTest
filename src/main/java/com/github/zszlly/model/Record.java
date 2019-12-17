package com.github.zszlly.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.zszlly.io.JsonObject;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

public class Record implements JsonObject {

    private final MethodHolder methodHolder;
    private final List<Argument> args;
    private final Argument returnValue;

    public Record(Method method, List<Argument> args, Argument returnValue) {
        this(new MethodHolder(method), args, returnValue);
    }

    @JsonCreator
    public Record(
            @JsonProperty("methodHolder") MethodHolder methodHolder,
            @JsonProperty("args") List<Argument> args,
            @JsonProperty("returnValue") Argument returnValue) {
        this.methodHolder = methodHolder;
        this.args = args;
        this.returnValue = returnValue;
    }

    @JsonIgnore
    public Method getMethod() {
        return methodHolder.getMethod();
    }

    @JsonProperty("methodHolder")
    public MethodHolder getMethodHolder() {
        return methodHolder;
    }

    @JsonProperty("args")
    public List<Argument> getArgs() {
        return args;
    }

    @JsonProperty("returnValue")
    public Argument getReturnValue() {
        return returnValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return Objects.equals(methodHolder, record.methodHolder) &&
                Objects.equals(args, record.args) &&
                Objects.equals(returnValue, record.returnValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(methodHolder, args, returnValue);
    }

    @Override
    public String toString() {
        return "Record{" +
                "methodHolder=" + methodHolder +
                ", args=" + args +
                ", returnValue=" + returnValue +
                '}';
    }
}
