package com.github.zszlly.recorder.asm;

import com.github.zszlly.builder.CaseBuilder;
import com.github.zszlly.util.NoTestUtils;

import java.util.Arrays;

public class NoTestASMRecorder {

    public static Object[] proxyArgs(Object... args) {
        CaseBuilder builder = (CaseBuilder) args[0];
        return Arrays.stream(args)
                .filter(arg -> arg != builder)
                .map(arg -> NoTestUtils.proxyInstance(arg, builder))
                .toArray();
    }

}
