package com.lh.nailweb.security.handler;

import com.alibaba.fastjson.JSON;
import com.lh.nailweb.util.JwtTokenUtil;
import com.lh.nailweb.util.MsgUtils;
import com.lh.nailweb.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther: loneyfall
 * @date: 2019/6/11
 * @description: 登录验证成功处理器
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenUtil.generateToken(userDetails);

        SecurityUtil.processRequest(request, response);
        renderToken(response, token);
    }

    /**
     * 渲染返回Token
     *
     * @param response
     * @param token
     * @throws IOException
     */
    private void renderToken(HttpServletResponse response, String token) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();
        String str = JSON.toJSONString(MsgUtils.success(token));
        out.write(str.getBytes("UTF-8"));
        out.flush();
        out.close();
    }
}
