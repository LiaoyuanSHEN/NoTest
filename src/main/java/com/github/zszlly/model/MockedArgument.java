package com.github.zszlly.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MockedArgument extends Argument {

    @JsonCreator
    public MockedArgument(@JsonProperty("instanceId") Integer instanceId) {
        super(instanceId);
    }

    @Override
    @JsonProperty("instanceId")
    public Integer getInstanceId() {
        return super.instanceId;
    }

    @Override
    public String toString() {
        return "MockedArgument{" +
                "instanceId=" + super.instanceId +
                '}';
    }
}
