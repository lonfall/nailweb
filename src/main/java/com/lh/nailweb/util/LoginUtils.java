package com.lh.nailweb.util;

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
}
