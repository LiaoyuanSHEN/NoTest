package com.github.zszlly.builder;

import com.github.zszlly.model.Action;
import com.github.zszlly.model.Argument;
import com.github.zszlly.model.Case;
import com.github.zszlly.model.Record;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CaseBuilder {

    private Map<Integer, Class<?>> mockedInstanceClassTable = new HashMap<>();
    private Map<String, Argument> fieldTable = new HashMap<>();
    private List<Action> actions = new LinkedList<>();
    private Record record;

    public Map<Integer, Class<?>> getMockedInstanceClassTable() {
        return mockedInstanceClassTable;
    }

    public void putMockedInstanceClass(Integer instanceId, Class<?> mockedClass) {
        mockedInstanceClassTable.put(instanceId, mockedClass);
    }

    public Map<String, Argument> getFieldTable() {
        return fieldTable;
    }

    public void putField(String fieldName, Argument argument) {
        fieldTable.put(fieldName, argument);
    }

    public List<Action> getActions() {
        return actions;
    }

    public void addAction(Action action) {
        actions.add(action);
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public Case build() {
        return new Case(mockedInstanceClassTable, fieldTable, actions, record);
    }

}
