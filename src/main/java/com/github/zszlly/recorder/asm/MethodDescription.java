package com.github.zszlly.recorder.asm;

import org.objectweb.asm.Type;

public class MethodDescription {

    private final String name;
    private final String owner;
    private final Type returnType;
    private final Type[] argumentsType;

    public MethodDescription(String name, String owner, String methodDescription) {
        this.name = name;
        this.owner = owner;
        this.returnType = Type.getReturnType(methodDescription);
        this.argumentsType = Type.getArgumentTypes(methodDescription);
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public Type getReturnType() {
        return returnType;
    }

    public Type[] getArgumentsType() {
        return argumentsType;
    }
}
