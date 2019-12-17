package com.github.zszlly.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;

public class VoidArgument extends Argument {

    @JsonCreator
    public VoidArgument() {
        super(-1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoidArgument argument = (VoidArgument) o;
        return Objects.equals(instanceId, argument.instanceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instanceId);
    }

}
