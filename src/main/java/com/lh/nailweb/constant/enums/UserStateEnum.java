package com.lh.nailweb.constant.enums;

/**
 * @auther: loneyfall
 * @date: 2019/8/9
 * @description:
 */
public enum UserStateEnum {
    NORMAL(0, "正常"),
    LOCK(1, "锁定");

    private final int value;
    private final String label;

    UserStateEnum(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }
}
