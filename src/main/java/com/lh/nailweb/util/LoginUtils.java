package com.lh.nailweb.util;

import com.lh.nailweb.entity.sys.User;
import com.lh.nailweb.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @auther: loneyfall
 * @date: 2019/7/2
 * @description: 登录相关工具类
 */
@Component
public class LoginUtils {

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public String getCookie(HttpServletRequest request, String name) {
        String token = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    token = cookie.getValue();
                }
            }
        }
        return token;
    }

    public User getCurrentUser(HttpServletRequest request, String name) {
        String token = getCookie(request, name);
        if (!StringUtils.isBlank(token)) {
            String username = jwtTokenUtil.getUsernameFromToken(token);
            if (!StringUtils.isBlank(username)) {
                User user = userService.getUserByUserName(username);
                return user;
            }
        }
        return null;
    }
}
