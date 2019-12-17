package com.github.zszlly.io;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.zszlly.model.Case;

import java.util.Collection;
import java.util.Objects;

public class CaseHolder implements JsonObject {

    private final Collection<Case> cases;

    @JsonCreator
    public CaseHolder(@JsonProperty("cases")Collection<Case> cases) {
        this.cases = cases;
    }

    @JsonProperty("cases")
    public Collection<Case> getCases() {
        return cases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaseHolder that = (CaseHolder) o;
        return Objects.equals(cases, that.cases);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cases);
    }

    @Override
    public String toString() {
        return "CaseHolder{" +
                "cases=" + cases +
                '}';
    }
}
