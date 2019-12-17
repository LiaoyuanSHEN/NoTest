package com.github.zszlly.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.zszlly.io.JsonObject;

import java.util.Objects;

public class Action implements JsonObject {

    private Integer instanceId;
    private Record record;

    @JsonCreator
    public Action(
            @JsonProperty("instanceId") Integer instanceId,
            @JsonProperty("record") Record record) {
        this.instanceId = instanceId;
        this.record = record;
    }

    @JsonProperty("instanceId")
    public Integer getInstanceId() {
        return instanceId;
    }

    @JsonProperty("record")
    public Record getRecord() {
        return record;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Action action = (Action) o;
        return Objects.equals(instanceId, action.instanceId) &&
                Objects.equals(record, action.record);
    }

    @Override
    public int hashCode() {
        return Objects.hash(instanceId, record);
    }

    @Override
    public String toString() {
        return "Action{" +
                "instanceId=" + instanceId +
                ", record=" + record +
                '}';
    }
}
