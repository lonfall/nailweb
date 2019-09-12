package com.lh.nailweb.util;

import com.lh.nailweb.constant.Constant;
import com.lh.nailweb.entity.BaseMsg;

/**
 * @auther: loneyfall
 * @date: 2019/6/11
 * @description: 消息返回工具类
 */
public class MsgUtils {

    public static BaseMsg success(Object obj) {
        BaseMsg<Object> result = new BaseMsg();
        result.setCode(Constant.HTTP_OK);
        result.setData(obj);
        return result;
    }

    public static BaseMsg success() {
        BaseMsg result = new BaseMsg();
        result.setCode(Constant.HTTP_OK);
        return result;
    }

    public static BaseMsg error(Integer code, String msg) {
        BaseMsg result = new BaseMsg();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static BaseMsg error(String msg) {
        BaseMsg result = new BaseMsg();
        result.setCode(Constant.HTTP_INTERNALSERVERERROR);
        result.setMsg(msg);
        return result;
    }
}
