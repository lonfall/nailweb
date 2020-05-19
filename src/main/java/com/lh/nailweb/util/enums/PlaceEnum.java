package com.lh.nailweb.util.enums;

/**
 * @auther: loneyfall
 * @date: 2020/5/12
 * @description: 场所枚举类
 */
public enum PlaceEnum {
    RIVER(1, "河流"),
    POOL(2, "水池"),
    CLIFF(3, "悬崖上"),
    ESTUARY(4, "出海口"),
    SEA(5, "大海"),
    WHARF(6, "码头");

    private int value;
    private String name;

    PlaceEnum(int value, String name) {
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
        for (PlaceEnum e : PlaceEnum.values()) {
            if (e.value == value) {
                return e.name;
            }
        }
        return "";
    }
}
