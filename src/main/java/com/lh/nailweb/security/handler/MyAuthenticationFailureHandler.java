package com.lh.nailweb.security.handler;

import com.alibaba.fastjson.JSON;
import com.lh.nailweb.constant.Constant;
import com.lh.nailweb.util.MsgUtils;
import com.lh.nailweb.util.SecurityUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther: loneyfall
 * @date: 2019/6/11
 * @description: 登录验证失败处理器
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        SecurityUtil.processRequest(request, response);
        renderFailure(response);
    }

    private void renderFailure(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();
        String str = JSON.toJSONString(MsgUtils.error(Constant.HTTP_UNAUTHORIZED, "用户名或密码错误！"));
        out.write(str.getBytes("UTF-8"));
        out.flush();
        out.close();
    }
}
