package com.lh.nailweb.vo.sys.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @auther: loneyfall
 * @date: 2019/8/9
 * @description: 用户注册VO
 */
@ApiModel(value = "用户注册VO", description = "用户注册VO")
public class UserRegisterVO {

    @ApiModelProperty(value = "用户名", name = "userName")
    private String userName;
    @ApiModelProperty(value = "密码", name = "password")
    private String password;
    @ApiModelProperty(value = "重复输入密码", name = "repeatPassword")
    private String repeatPassword;
    @ApiModelProperty(value = "昵称", name = "nickname")
    private String nickname;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
