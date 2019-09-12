package com.lh.nailweb.security;

import com.lh.nailweb.entity.JwtUser;
import com.lh.nailweb.entity.sys.User;
import com.lh.nailweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @auther: loneyfall
 * @date: 2019/6/10
 * @description: 用户认证服务类
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private IUserService userService;

    /**
     * 获取用户信息与权限
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUserName(username);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        return new JwtUser(username, user.getPassword(), user.getState(), authorities);
    }
}
