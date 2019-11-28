package com.lh.nailweb.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @auther: loneyfall
 * @date: 2019/7/31
 * @description: 基础实体类
 */
@ApiModel(value = "基础实体类", description = "基础实体类")
public class BaseEntity {
    @ApiModelProperty(value = "创建时间", name = "createTime")
    // 创建时间
    private Date createTime;
    @ApiModelProperty(value = "更新时间", name = "updateTime")
    // 更新时间
    private Date updateTime;
    @ApiModelProperty(value = "删除标记 0：未删除 1：已删除", name = "delFlag", example = "0")
    // 删除标记 0：未删除 1：已删除
    private int delFlag;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }
}
