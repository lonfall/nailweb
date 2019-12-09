package com.lh.nailweb.vo.page;

import com.lh.nailweb.vo.BasePage;
import com.lh.nailweb.vo.sys.user.UserVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @auther: loneyfall
 * @date: 2019/9/2
 * @description: 用户分页类
 */
@ApiModel(value = "用户分页类", description = "用户分页类")
public class UserPage extends BasePage<UserVO> {
    @ApiModelProperty(value = "用户名", name = "username")
    // 用户名
    private String username;
    @ApiModelProperty(value = "昵称", name = "nickname")
    // 昵称
    private String nickname;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
