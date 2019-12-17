package com.github.zszlly.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.zszlly.util.ClassUtils;

import java.util.Objects;

public class PrimitiveArgument extends Argument {

    private final Class<?> type;
    private final String value;

    @JsonCreator
    public PrimitiveArgument(
            @JsonProperty("type") String typeName,
            @JsonProperty("value") String value) {
        this(ClassUtils.forName(typeName), value);
    }

    public PrimitiveArgument(Class<?> type, String value) {
        super(-1);
        this.type = type;
        this.value = value;
    }

    @JsonIgnore
    public Class<?> getType() {
        return type;
    }

    @JsonIgnore
    public Object getValueInstance() {
        if (type == boolean.class || type == Boolean.class) {
            return Boolean.valueOf(value);
        }
        if (type == char.class || type == Character.class) {
            if (value.length() > 1) {
                throw new IllegalArgumentException("Wrong value, want a character but get [" + value + "].");
            }
            return value.charAt(0);
        }
        if (type == byte.class || type == Byte.class) {
            return Byte.valueOf(value);
        }
        if (type == short.class || type == Short.class) {
            return Short.valueOf(value);
        }
        if (type == int.class || type == Integer.class) {
            return Integer.valueOf(value);
        }
        if (type == long.class || type == Long.class) {
            return Long.valueOf(value);
        }
        if (type == float.class || type == Float.class) {
            return Float.valueOf(value);
        }
        if (type == double.class || type == Double.class) {
            return Double.valueOf(value);
        }
        if (type == String.class) {
            return value;
        }
        throw new IllegalArgumentException("Unsupported mock class: " + type.getName());
    }

    @JsonProperty("type")
    public String getTypeName() {
        return type.getName();
    }

    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PrimitiveArgument that = (PrimitiveArgument) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }

    @Override
    public String toString() {
        return "PrimitiveArgument{" +
                "type=" + type +
                ", value='" + value + '\'' +
                ", instanceId=" + instanceId +
                '}';
    }
}
