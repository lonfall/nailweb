package com.lh.nailweb.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @auther: loneyfall
 * @date: 2019/9/3
 * @description: 授权相关工具
 */
public class SecurityUtil {

    /**
     * 在返回体中加上跨域信息
     *
     * @param request
     * @param response
     */
    public static void processRequest(HttpServletRequest request, HttpServletResponse response) {
        if (request.getHeader(HttpHeaders.ORIGIN) != null) {
            ServletServerHttpResponse serverResponse = new ServletServerHttpResponse(response);

            HttpHeaders respHeader = serverResponse.getHeaders();
            if (respHeader.getAccessControlAllowOrigin() != null) {
                // 如果返回体中有Origin则跳过
                return;
            }

            ServletServerHttpRequest serverRequest = new ServletServerHttpRequest(request);
            if (WebUtils.isSameOrigin(serverRequest)) {
                // 如果是同一来源则跳过
                return;
            }

            respHeader.addAll(HttpHeaders.VARY, Arrays.asList(HttpHeaders.ORIGIN,
                    HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS));

            String requestOrigin = serverRequest.getHeaders().getOrigin();

            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, requestOrigin);
            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
        }
    }
}
