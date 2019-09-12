package com.lh.nailweb.security.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @auther: loneyfall
 * @date: 2019/8/26
 * @description: 用于去掉cookie中的jsessionid
 */
@Component
public class MyDisableUrlSessionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // 跳过非HTTP请求
        if (!(request instanceof HttpServletRequest)) {
            chain.doFilter(request, response);
            return;
        }

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 如果URL中有session id，则清除session
        if (httpRequest.isRequestedSessionIdFromURL()) {
            HttpSession session = httpRequest.getSession();
            if (session != null)
                session.invalidate();
        }

        // 覆盖响应以移除URL编码
        HttpServletResponseWrapper wrappedResponse = new HttpServletResponseWrapper(httpResponse) {
            @Override
            public String encodeRedirectUrl(String url) {
                return url;
            }

            public String encodeRedirectURL(String url) {
                return url;
            }

            public String encodeUrl(String url) {
                return url;
            }

            public String encodeURL(String url) {
                return url;
            }
        };

        // 处理下一个请求
        chain.doFilter(request, wrappedResponse);
    }

    @Override
    public void destroy() {
    }
}
