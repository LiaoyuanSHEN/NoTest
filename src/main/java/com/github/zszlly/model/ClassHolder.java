package com.github.zszlly.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ClassHolder {
    private final Integer instanceId;
    private final String className;

    @JsonCreator
    public ClassHolder(
            @JsonProperty("instanceId") Integer instanceId,
            @JsonProperty("className") String className) {
        this.instanceId = instanceId;
        this.className = className;
    }

    @JsonProperty("instanceId")
    public Integer getInstanceId() {
        return instanceId;
    }

    @JsonProperty("className")
    public String getClassName() {
        return className;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassHolder that = (ClassHolder) o;
        return Objects.equals(instanceId, that.instanceId) &&
                Objects.equals(className, that.className);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instanceId, className);
    }

    @Override
    public String toString() {
        return "ClassHolder{" +
                "instanceId=" + instanceId +
                ", className='" + className + '\'' +
                '}';
    }
}