package com.lh.nailweb.security.handler;

import com.alibaba.fastjson.JSON;
import com.lh.nailweb.constant.Constant;
import com.lh.nailweb.util.MsgUtils;
import com.lh.nailweb.util.SecurityUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther: loneyfall
 * @date: 2019/6/11
 * @description: 权限不足处理器
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        SecurityUtil.processRequest(request, response);
        renderAccessDenied(response);
    }

    private void renderAccessDenied(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = response.getOutputStream();
        String str = JSON.toJSONString(MsgUtils.error(Constant.HTTP_FORBIDDEN, "用户权限不足！"));
        out.write(str.getBytes("UTF-8"));
        out.flush();
        out.close();
    }
}
