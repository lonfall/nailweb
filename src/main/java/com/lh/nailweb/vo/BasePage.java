package com.lh.nailweb.vo;

import io.swagger.annotations.ApiModelProperty;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @auther: loneyfall
 * @date: 2019/9/2
 * @description: 基础分页类
 */
public class BasePage<T> {
    @ApiModelProperty(value = "当前页", name = "current")
    private int current;
    @ApiModelProperty(value = "每页条数", name = "size")
    private int size;
    @ApiModelProperty(value = "总条数", name = "total")
    private int total;
    @ApiModelProperty(value = "数据", name = "data")
    private List<T> data;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
