package com.lh.nailweb.constant.enums;

/**
 * @auther: loneyfall
 * @date: 2019/8/9
 * @description:
 */
public enum DelFlagEnum {
    NORMAL(0, "正常"),
    DEL(1, "删除");

    private final int value;
    private final String label;

    DelFlagEnum(int value, String label) {
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
