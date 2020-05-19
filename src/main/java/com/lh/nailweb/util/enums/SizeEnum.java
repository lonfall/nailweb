package com.lh.nailweb.util.enums;

/**
 * @auther: loneyfall
 * @date: 2020/5/12
 * @description: 尺寸枚举
 */
public enum SizeEnum {
    SMALL(1, "小"),
    LITTLE_SMALL(2, "稍小"),
    MEDIUM(3, "中"),
    LARGE(4, "大"),
    LITTLE_LARGE(5, "稍大"),
    EXTRA_LARGE(6, "特大");

    private int value;
    private String name;

    SizeEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static String getName(int value) {
        for (SizeEnum e : SizeEnum.values()) {
            if (e.value == value) {
                return e.name;
            }
        }
        return "";
    }
}
