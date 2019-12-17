package com.github.zszlly.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.zszlly.io.JsonObject;
import com.github.zszlly.util.ClassUtils;

import java.util.*;

public class Case implements JsonObject {

    private final Map<Integer, Class<?>> mockedInstanceClassTable;
    private final Map<String, Argument> fieldTable;
    private final List<Action> actions;
    private final Record record;

    @JsonCreator
    public Case(
            @JsonProperty("mockedInstanceClassHolderList") List<ClassHolder> mockedInstanceClassHolderList,
            @JsonProperty("fieldTable") Map<String, Argument> fieldTable,
            @JsonProperty("actions") List<Action> actions,
            @JsonProperty("record") Record record) {
        this(toMockedInstanceClassTable(mockedInstanceClassHolderList), fieldTable, actions, record);
    }

    public Case(Map<Integer, Class<?>> mockedInstanceClassTable, Map<String, Argument> fieldTable, List<Action> actions, Record record) {
        this.mockedInstanceClassTable = mockedInstanceClassTable;
        this.fieldTable = fieldTable;
        this.actions = actions;
        this.record = record;
    }

    private static Map<Integer, Class<?>> toMockedInstanceClassTable(List<ClassHolder> mockedInstanceClassHolderList) {
        Map<Integer, Class<?>> mockedInstanceClassTable = new HashMap<>();
        mockedInstanceClassHolderList.forEach(classHolder -> mockedInstanceClassTable.put(classHolder.getInstanceId(), ClassUtils.forName(classHolder.getClassName())));
        return mockedInstanceClassTable;
    }

    @JsonIgnore
    public Map<Integer, Class<?>> getMockedInstanceClassTable() {
        return mockedInstanceClassTable;
    }

    @JsonProperty("mockedInstanceClassHolderList")
    public List<ClassHolder> getMockedInstanceClassHolderList() {
        List<ClassHolder> mockedInstanceClassHolderList = new LinkedList<>();
        mockedInstanceClassTable.forEach((instanceId, clazz) -> mockedInstanceClassHolderList.add(new ClassHolder(instanceId, clazz.getName())));
        return mockedInstanceClassHolderList;
    }

    @JsonProperty("fieldTable")
    public Map<String, Argument> getFieldTable() {
        return fieldTable;
    }

    @JsonProperty("actions")
    public List<Action> getActions() {
        return actions;
    }

    @JsonProperty("record")
    public Record getRecord() {
        return record;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Case aCase = (Case) o;
        return Objects.equals(mockedInstanceClassTable, aCase.mockedInstanceClassTable) &&
                Objects.equals(fieldTable, aCase.fieldTable) &&
                Objects.equals(actions, aCase.actions) &&
                Objects.equals(record, aCase.record);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mockedInstanceClassTable, fieldTable, actions, record);
    }

    @Override
    public String toString() {
        return "Case{" +
                "mockedInstanceClassTable=" + mockedInstanceClassTable +
                ", fieldTable=" + fieldTable +
                ", actions=" + actions +
                ", record=" + record +
                '}';
    }
}
