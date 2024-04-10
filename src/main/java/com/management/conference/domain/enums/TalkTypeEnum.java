package com.management.conference.domain.enums;

public enum TalkTypeEnum {
    MINUTES("min", 1),
    LIGHTNING("lightning", 5);

    private String name;
    private int value;

    TalkTypeEnum(String name, int value) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
