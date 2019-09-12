package com.lh.nailweb.controller;

import com.lh.nailweb.util.JwtTokenUtil;
import com.lh.nailweb.util.LoginUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @auther: loneyfall
 * @date: 2019/6/11
 * @description: 登录控制器
 */
@Api(description = "登录接口")
@Controller
public class LoginController {

    @Autowired
    private LoginUtils loginUtils;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 登录页面
     *
     * @return
     */
    @ApiIgnore
    @RequestMapping(method = RequestMethod.GET, path = "/login")
    public String login() {
        return "login";
    }


    /**
     * 首页
     *
     * @return
     */
    @ApiIgnore
    @RequestMapping(method = RequestMethod.GET, path = {"/index", "/"})
    public String indexGet(HttpServletRequest request, HttpServletResponse response, @RequestHeader(value = "token", required = false) String token) throws IOException {
        // 如果header中没有获取到token则在cookie中获取
        if (StringUtils.isEmpty(token)) {
            token = loginUtils.getCookie(request, jwtTokenUtil.getHeader());
        }
        HttpSession session = request.getSession();
        session.setAttribute("token", token);
        return "index";
    }
}
