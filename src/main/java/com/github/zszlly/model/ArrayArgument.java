package com.github.zszlly.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class ArrayArgument extends Argument {

    private final String elementTypeName;
    private final List<Argument> elements;

    @JsonCreator
    public ArrayArgument(
            @JsonProperty("instanceId") int instanceId,
            @JsonProperty("elementTypeName") String elementTypeName,
            @JsonProperty("elements") List<Argument> elements) {
        super(instanceId);
        this.elementTypeName = elementTypeName;
        this.elements = elements;
    }

    @JsonProperty("elementTypeName")
    public String getElementTypeName() {
        return elementTypeName;
    }

    @JsonProperty("elements")
    public List<Argument> getElements() {
        return elements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ArrayArgument that = (ArrayArgument) o;
        return Objects.equals(elementTypeName, that.elementTypeName) &&
                Objects.equals(elements, that.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), elementTypeName, elements);
    }

    @Override
    public String toString() {
        return "ArrayArgument{" +
                "elementTypeName='" + elementTypeName + '\'' +
                ", elements=" + elements +
                ", instanceId=" + instanceId +
                '}';
    }
}
