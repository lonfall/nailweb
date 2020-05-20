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
}
