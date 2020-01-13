package com.lh.nailweb.vo.sys.menu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @auther: loneyfall
 * @date: 2019/9/25
 * @description: 编辑菜单VO
 */
@ApiModel(value = "编辑菜单VO", description = "编辑菜单VO")
public class MenuEditVO {
    private long id;
    @ApiModelProperty(value = "父节点ID，一级菜单为0", name = "pid", example = "0")
    private long pid;
    @ApiModelProperty(value = "菜单名称", name = "name")
    private String name;
    @ApiModelProperty(value = "菜单链接", name = "url")
    private String url;
    @ApiModelProperty(value = "权限标志", name = "permission")
    private String permission;
    @ApiModelProperty(value = "类型 0：目录 1：菜单 2：权限", name = "type", example = "0")
    private int type;
    @ApiModelProperty(value = "图标", name = "icon")
    private String icon;
    @ApiModelProperty(value = "排序", name = "sort", example = "1")
    private int sort;
    @ApiModelProperty(value = "是否隐藏", name = "hide", example = "false")
    private boolean hide;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }
}
