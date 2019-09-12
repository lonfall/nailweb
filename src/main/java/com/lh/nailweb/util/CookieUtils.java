package com.lh.nailweb.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @auther: loneyfall
 * @date: 2019/8/8
 * @description:
 */
@Component
public class CookieUtils {

    private static Long expiration;

    @Value("${jwt.expiration}")
    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public static String getCookie(HttpServletRequest request, String cookieName) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }

    public static void writeCookie(HttpServletResponse response, String cookieName, String value) {
        Cookie cookie = new Cookie(cookieName, value);
//        int day = 30;
        cookie.setPath("/");
//        cookie.setMaxAge(day * 24 * 60 * 60);
        cookie.setMaxAge((int) (expiration / 1000));
        response.addCookie(cookie);
    }

}
