package com.lh.nailweb.constant.enums;

/**
 * @auther: loneyfall
 * @date: 2019/8/9
 * @description: 菜单类型枚举
 */
public enum MenuTypeEnum {
    LIST(0, "目录"),
    MENU(1, "菜单"),
    AUTH(2, "权限");

    private final int value;
    private final String label;

    MenuTypeEnum(int value, String label) {
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
