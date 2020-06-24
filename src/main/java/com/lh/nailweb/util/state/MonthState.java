package com.lh.nailweb.util.state;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static String valueToString(int value) {
        if (value == 4095) {
            return "全年";
        } else if (value > 4095 || value < 0) {
            return "数据超出范围";
        }
        boolean head = false;
        int start = -1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 12; i++) {
            if (((1 << i) & value) != 0) {
                if (i == 0) {
                    head = true;
                } else if (i == 11) {
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
        for (int i = 0; i < 12; i++) {
            if (map.get(i) != null) {
                int x = i + 1;
                int y = map.get(i) + 1;
                if (!first) {
                    buffer.append(" ");
                }
                if (x != y) {
                    buffer.append(x + "月-" + y + "月");
                } else {
                    buffer.append(x + "月");
                }
                first = false;
            }
        }
        return buffer.toString();
    }
}
