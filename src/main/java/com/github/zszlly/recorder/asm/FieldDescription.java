package com.github.zszlly.recorder.asm;

public class FieldDescription {

    private final String name;
    private final String owner;
    private final String typeDescriptor;

    public FieldDescription(String name, String owner, String typeDescriptor) {
        this.name = name;
        this.owner = owner;
        this.typeDescriptor = typeDescriptor;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public String getTypeDescriptor() {
        return typeDescriptor;
    }
}
