package com.lh.nailweb.security.handler;

import com.alibaba.fastjson.JSON;
import com.lh.nailweb.constant.Constant;
import com.lh.nailweb.util.MsgUtils;
import com.lh.nailweb.util.SecurityUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther: loneyfall
 * @date: 2019/6/11
 * @description: 身份校验失败处理器，如 token 错误
 */
@Component
public class UnauthorizedEntryPointHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        SecurityUtil.processRequest(request, response);
        renderUnauthorized(request, response);
    }

    private void renderUnauthorized(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        // 重定向到登录页
//        response.sendRedirect(request.getContextPath() + "/login");
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();
        String str = JSON.toJSONString(MsgUtils.error(Constant.HTTP_UNAUTHORIZED, "身份验证错误！"));
        out.write(str.getBytes("UTF-8"));
        out.flush();
        out.close();
    }
}
