package com.lh.nailweb.vo.sys.role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @auther: loneyfall
 * @date: 2019/8/12
 * @description: 编辑角色VO
 */
@ApiModel(value = "编辑角色VO", description = "编辑角色VO")
public class RoleEditVO {

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
