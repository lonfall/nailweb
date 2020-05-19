package com.lh.nailweb.entity.fish;

import com.lh.nailweb.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @auther: loneyfall
 * @date: 2020/4/30
 * @description: 鱼类
 */
@ApiModel(value = "鱼类", description = "鱼类")
public class Fish extends BaseEntity {
    private long id;
    @ApiModelProperty(value = "名称", name = "name")
    private String name;
    @ApiModelProperty(value = "图片", name = "img")
    private String img;
    @ApiModelProperty(value = "价格", name = "price", example = "400")
    private Integer price;
    @ApiModelProperty(value = "南半球出现月份", name = "south_month", example = "1")
    private Integer south_month;
    @ApiModelProperty(value = "北半球出现月份", name = "north_month", example = "1")
    private Integer north_month;
    @ApiModelProperty(value = "出现时间段", name = "time", example = "1")
    private Integer time;
    @ApiModelProperty(value = "场所", name = "place", example = "1")
    private Integer place;
    @ApiModelProperty(value = "鱼影大小", name = "size", example = "1")
    private Integer size;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getSouth_month() {
        return south_month;
    }

    public void setSouth_month(Integer south_month) {
        this.south_month = south_month;
    }

    public Integer getNorth_month() {
        return north_month;
    }

    public void setNorth_month(Integer north_month) {
        this.north_month = north_month;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
