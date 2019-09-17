package com.lh.nailweb.vo.page;

import com.lh.nailweb.vo.BasePage;
import com.lh.nailweb.vo.sys.role.RoleVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * @auther: loneyfall
 * @date: 2019/9/16
 * @description: 角色分页类
 */
public class RolePage extends BasePage<RoleVO> {
    @ApiModelProperty(value = "角色名称", name = "name")
    private String name;
    @ApiModelProperty(value = "角色备注", name = "remark")
    private String remark;

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
