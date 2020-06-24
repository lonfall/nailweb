package com.lh.nailweb.util.state;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther: loneyfall
 * @date: 2020/5/12
 * @description: 时间状态类
 */
public enum TimeState {
    AM_00(1 << 0, "上午零点"),
    AM_01(1 << 1, "上午一点"),
    AM_02(1 << 2, "上午二点"),
    AM_03(1 << 3, "上午三点"),
    AM_04(1 << 4, "上午四点"),
    AM_05(1 << 5, "上午五点"),
    AM_06(1 << 6, "上午六点"),
    AM_07(1 << 7, "上午七点"),
    AM_08(1 << 8, "上午八点"),
    AM_09(1 << 9, "上午九点"),
    AM_10(1 << 10, "上午十点"),
    AM_11(1 << 11, "上午十一点"),
    AM_12(1 << 12, "上午十二点"),
    PM_01(1 << 13, "下午一点"),
    PM_02(1 << 14, "下午二点"),
    PM_03(1 << 15, "下午三点"),
    PM_04(1 << 16, "下午四点"),
    PM_05(1 << 17, "下午五点"),
    PM_06(1 << 18, "下午六点"),
    PM_07(1 << 19, "下午七点"),
    PM_08(1 << 20, "下午八点"),
    PM_09(1 << 21, "下午九点"),
    PM_10(1 << 22, "下午十点"),
    PM_11(1 << 23, "下午十一点"),
    PM_12(1 << 24, "下午十二点");

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

    public static String valueToString(int value) {
        if (value == 33554431) {
            return "全天";
        } else if (value > 33554431 || value < 0) {
            return "数据超出范围";
        }
        boolean head = false;
        int start = -1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 25; i++) {
            if (((1 << i) & value) != 0) {
                if (i == 0) {
                    head = true;
                } else if (i == 24) {
                    if (head) {
                        int t = map.get(0);
                        if (start < 0) {
                            map.put(i, t);
                        } else {
                            map.put(start, t);
                        }
                        map.remove(0);
                    } else {
                        if (start < 0) {
                            map.put(i, i);
                        } else {
                            map.put(start, i);
                        }
                    }
                    start = -1;
                    break;
                }
                if (start < 0) {
                    start = i;
                }
                if (((1 << (i + 1)) & value) == 0) {
                    map.put(start, i);
                    start = -1;
                }
            }
        }
        if (start >= 0) {
            map.put(start, start);
        }
        StringBuffer buffer = new StringBuffer();
        boolean first = true;
        for (int i = 0; i < 25; i++) {
            if (map.get(i) != null) {
                int x = i;
                int y = map.get(i);
                if (!first) {
                    buffer.append(" ");
                }
                if (x != y) {
                    buffer.append(x + "点-" + y + "点");
                } else {
                    buffer.append(x + "点");
                }
                first = false;
            }
        }
        return buffer.toString();
    }
}
