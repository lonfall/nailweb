package com.lh.nailweb.util.state;

/**
 * @auther: loneyfall
 * @date: 2020/5/12
 * @description: 月份状态类
 */
public enum MonthState {
    January(1 << 0, "一月"),
    February(1 << 1, "二月"),
    March(1 << 2, "三月"),
    April(1 << 3, "四月"),
    May(1 << 4, "五月"),
    June(1 << 5, "六月"),
    July(1 << 6, "七月"),
    August(1 << 7, "八月"),
    September(1 << 8, "九月"),
    October(1 << 9, "十月"),
    November(1 << 10, "十一月"),
    December(1 << 11, "十二月");

    private int value;
    private String name;

    MonthState(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static int createState(MonthState... args) {
        int result = 0;
        for (MonthState arg : args) {
            result = result | arg.value;
        }
        return result;
    }

    public static boolean hasState(MonthState state, int value) {
        return (state.value & value) != 0;
    }

    public static int addState(MonthState state, int value) {
        return state.value | value;
    }

    public static int removeState(MonthState state, int value) {
        return state.value ^ value;
    }
}
