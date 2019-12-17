package com.github.zszlly.io;

import com.github.zszlly.model.Case;

import java.util.Collection;

public interface CaseSaver {

    void addCase(Case c);

    void addCases(Collection<Case> cases);

    void save();

}
