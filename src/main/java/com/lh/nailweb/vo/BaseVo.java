package com.lh.nailweb.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @auther: loneyfall
 * @date: 2019/9/2
 * @description: 基础VO类
 */
@ApiModel(value = "基础VO类", description = "基础VO类")
public class BaseVo {
    @ApiModelProperty(value = "创建时间", name = "createTime")
    // 创建时间
    private Date createTime;
    @ApiModelProperty(value = "更新时间", name = "updateTime")
    // 更新时间
    private Date updateTime;
    @ApiModelProperty(value = "删除标记 0：未删除 1：已删除", name = "delFlag")
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
