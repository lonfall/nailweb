package com.lh.nailweb.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @auther: loneyfall
 * @date: 2019/6/11
 * @description: 消息返回类型
 */
@ApiModel(value = "基础消息类", description = "基础消息类")
public class BaseMsg<T> {
    @ApiModelProperty(value = "状态代码", name = "code", example = "200")
    private Integer code;
    @ApiModelProperty(value = "错误信息", name = "msg")
    private String msg;
    @ApiModelProperty(value = "消息体", name = "data")
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
