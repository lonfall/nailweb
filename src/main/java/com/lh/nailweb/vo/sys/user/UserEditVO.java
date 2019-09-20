package com.lh.nailweb.vo.sys.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @auther: loneyfall
 * @date: 2019/9/18
 * @description: 编辑用户VO
 */
@ApiModel(value = "编辑用户VO", description = "编辑用户VO")
public class UserEditVO {
    private long id;
    @ApiModelProperty(value = "昵称", name = "nickname")
    // 昵称
    private String nickname;
    @ApiModelProperty(value = "头像", name = "avatar")
    // 头像
    private String avatar;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
