package com.lh.nailweb.vo.page.fish;

import com.lh.nailweb.entity.fish.Fish;
import com.lh.nailweb.vo.BasePage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @auther: loneyfall
 * @date: 2020/5/13
 * @description: 鱼类分页类
 */
@ApiModel(value = "鱼类分页类", description = "鱼类分页类")
public class FishPage extends BasePage<Fish> {
    @ApiModelProperty(value = "鱼类名称", name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
