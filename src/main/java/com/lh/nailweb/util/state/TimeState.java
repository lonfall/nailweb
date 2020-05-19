package com.lh.nailweb.util.state;

/**
 * @auther: loneyfall
 * @date: 2020/5/12
 * @description: 时间状态类
 */
public enum TimeState {
    AM_01(1 << 0, "上午一点"),
    AM_02(1 << 1, "上午二点"),
    AM_03(1 << 2, "上午三点"),
    AM_04(1 << 3, "上午四点"),
    AM_05(1 << 4, "上午五点"),
    AM_06(1 << 5, "上午六点"),
    AM_07(1 << 6, "上午七点"),
    AM_08(1 << 7, "上午八点"),
    AM_09(1 << 8, "上午九点"),
    AM_10(1 << 9, "上午十点"),
    AM_11(1 << 10, "上午十一点"),
    AM_12(1 << 11, "上午十二点"),
    PM_01(1 << 12, "下午一点"),
    PM_02(1 << 13, "下午二点"),
    PM_03(1 << 14, "下午三点"),
    PM_04(1 << 15, "下午四点"),
    PM_05(1 << 16, "下午五点"),
    PM_06(1 << 17, "下午六点"),
    PM_07(1 << 18, "下午七点"),
    PM_08(1 << 19, "下午八点"),
    PM_09(1 << 20, "下午九点"),
    PM_10(1 << 21, "下午十点"),
    PM_11(1 << 22, "下午十一点"),
    PM_12(1 << 23, "下午十二点");

    private int value;
    private String name;

    TimeState(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static int createState(TimeState... args) {
        int result = 0;
        for (TimeState arg : args) {
            result = result | arg.value;
        }
        return result;
    }

    public static boolean hasState(TimeState state, int value) {
        return (state.value & value) != 0;
    }

    public static int addState(TimeState state, int value) {
        return state.value | value;
    }

    public static int removeState(TimeState state, int value) {
        return state.value ^ value;
    }
}
