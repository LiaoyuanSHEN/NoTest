package com.github.zszlly.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.zszlly.util.ClassUtils;

import java.util.Objects;

public class GeneratedByMethodArgument extends Argument {

    private final Class<?> objectClass;

    @JsonCreator
    public GeneratedByMethodArgument(@JsonProperty("objectClass") String objectClassName) {
        this(ClassUtils.forName(objectClassName));
    }

    public GeneratedByMethodArgument(Class<?> objectClass) {
        super(-1);
        this.objectClass = objectClass;
    }

    @JsonIgnore
    public Class<?> getObjectClass() {
        return objectClass;
    }

    @JsonProperty("objectClass")
    public String getObjectClassName() {
        return objectClass.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GeneratedByMethodArgument that = (GeneratedByMethodArgument) o;
        return Objects.equals(objectClass, that.objectClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), objectClass);
    }

    @Override
    public String toString() {
        return "GeneratedByMethodArgument{" +
                "objectClass=" + objectClass +
                ", instanceId=" + instanceId +
                '}';
    }
}
