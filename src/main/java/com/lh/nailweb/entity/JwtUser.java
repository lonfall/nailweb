package com.lh.nailweb.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @auther: loneyfall
 * @date: 2019/6/11
 * @description: jwt认证用户权限类
 */
@ApiModel(value = "用户鉴权类", description = "jwt用户鉴权")
public class JwtUser implements UserDetails {
    @ApiModelProperty(value = "用户名", name = "username", required = true)
    private String username;
    @ApiModelProperty(value = "密码", name = "password", required = true)
    private String password;
    @ApiModelProperty(value = "权限列表", name = "authorities")
    private Collection<? extends GrantedAuthority> authorities;
    @ApiModelProperty(value = "状态", name = "state")
    private Integer state;

    public JwtUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public JwtUser(String username, String password, Integer state, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.state = state;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    /**
     * 账户是否过期
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 用户是否被锁定
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        switch (state) {
            case 0:
                return true;
            case 1:
                return false;
        }
        return false;
    }

    /**
     * 密码是否过期
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否启用
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
