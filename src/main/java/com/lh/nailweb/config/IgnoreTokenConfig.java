package com.lh.nailweb.config;

import org.springframework.context.annotation.Configuration;

/**
 * @auther: loneyfall
 * @date: 2020/5/20
 * @description: 配置不鉴权的接口
 */
@Configuration
public class IgnoreTokenConfig {

    private final String[] ignoreServletPath = new String[]{
            "/fish/list"
    };

    public boolean isIgnore(String servletPath) {
        for (int i = 0; i < ignoreServletPath.length; i++) {
            String ignorePath = ignoreServletPath[i];
            if (ignorePath.equals(servletPath)) {
                return true;
            }
        }
        return false;
    }

    public String[] getIgnoreServletPath() {
        return ignoreServletPath;
    }
}
