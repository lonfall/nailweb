package com.lh.nailweb.vo.sys.user;

import com.lh.nailweb.vo.BaseVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @auther: loneyfall
 * @date: 2019/9/2
 * @description: 用户VO类
 */
@ApiModel(value = "用户详情VO", description = "用户详情VO")
public class UserVO extends BaseVo {
    private long id;
    @ApiModelProperty(value = "用户名", name = "username")
    // 用户名
    private String username;
    @ApiModelProperty(value = "状态", name = "state")
    // 状态
    private int state;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
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
