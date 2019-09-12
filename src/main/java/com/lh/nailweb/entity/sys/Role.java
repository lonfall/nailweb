package com.lh.nailweb.entity.sys;

import com.lh.nailweb.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @auther: loneyfall
 * @date: 2019/8/12
 * @description: 角色类
 */
@ApiModel(value = "角色类", description = "角色类")
public class Role extends BaseEntity {
    private long id;
    @ApiModelProperty(value = "角色名称", name = "name")
    private String name;
    @ApiModelProperty(value = "角色备注", name = "remark")
    private String remark;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
