package com.lh.nailweb.vo.fish;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @auther: loneyfall
 * @date: 2020/5/13
 * @description: 筛选鱼类VO
 */
@ApiModel(value = "筛选鱼类VO", description = "筛选鱼类VO")
public class FishVO {
    @ApiModelProperty(value = "地区,1=北半球,2=南半球", name = "region", example = "1")
    private Integer region;
    @ApiModelProperty(value = "出现时间段", name = "time", example = "1")
    private Integer time;
    @ApiModelProperty(value = "出现月份", name = "month", example = "1")
    private Integer month;
    @ApiModelProperty(value = "场所", name = "place", example = "1")
    private Integer place;
    @ApiModelProperty(value = "是否已捕捉", name = "have", example = "true")
    private Boolean have;

    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
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

    public Boolean getHave() {
        return have;
    }

    public void setHave(Boolean have) {
        this.have = have;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}
