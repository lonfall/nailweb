package com.lh.nailweb.vo.fish;

import io.swagger.annotations.ApiModelProperty;

/**
 * @auther: loneyfall
 * @date: 2020/5/20
 * @description:
 */
public class FishListVO {
    private long id;
    @ApiModelProperty(value = "名称", name = "name")
    private String name;
    @ApiModelProperty(value = "图片", name = "img")
    private String img;
    @ApiModelProperty(value = "价格", name = "price")
    private Integer price;
    @ApiModelProperty(value = "南半球出现月份", name = "south_month")
    private String south_month;
    @ApiModelProperty(value = "北半球出现月份", name = "north_month")
    private String north_month;
    @ApiModelProperty(value = "出现时间段", name = "time")
    private String time;
    @ApiModelProperty(value = "场所", name = "place")
    private String place;
    @ApiModelProperty(value = "鱼影大小", name = "size")
    private String size;
    @ApiModelProperty(value = "是否已捕捉", name = "have", example = "true")
    private Boolean have;

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

    public String getSouth_month() {
        return south_month;
    }

    public void setSouth_month(String south_month) {
        this.south_month = south_month;
    }

    public String getNorth_month() {
        return north_month;
    }

    public void setNorth_month(String north_month) {
        this.north_month = north_month;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Boolean getHave() {
        return have;
    }

    public void setHave(Boolean have) {
        this.have = have;
    }
}
