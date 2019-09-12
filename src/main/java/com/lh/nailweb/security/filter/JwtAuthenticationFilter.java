package com.lh.nailweb.security.filter;

import com.alibaba.fastjson.JSON;
import com.lh.nailweb.util.CookieUtils;
import com.lh.nailweb.util.JwtTokenUtil;
import com.lh.nailweb.util.LoginUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther: loneyfall
 * @date: 2019/6/12
 * @description: token校验
 */
@Component
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private static final String FILTER_APPLICATION = "__nailweb_spring_security_JwtAuthenticationFilter";

    private Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private LoginUtils loginUtils;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 如果是登录页则不需要校验token
        if ("/login".equals(request.getServletPath())) {
            chain.doFilter(request, response);
            return;
        }
        // 如果是option请求则不需要校验token
        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            chain.doFilter(request, response);
            return;
        }
        //获取token，先在header中取，如果取不到就在cookies中取
        String token = request.getHeader(jwtTokenUtil.getHeader());
        if (StringUtils.isEmpty(token)) {
            token = loginUtils.getCookie(request, jwtTokenUtil.getHeader());
            if (StringUtils.isEmpty(token)) {
                logger.warn("未获取到token，校验失败！");
                chain.doFilter(request, response);
                return;
            }
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 如果令牌即将过期，则更新令牌
        if (jwtTokenUtil.isTokenSoonToExpired(token)) {
            String refreshToken = jwtTokenUtil.refreshToken(token);
            // 设置cookie
            CookieUtils.writeCookie(response, jwtTokenUtil.getHeader(), refreshToken);
            // 设置返回头，如果客户端不支持cookie那么需要客户端刷新token
            response.addHeader("refreshToken", refreshToken);
        }

        chain.doFilter(request, response);
    }

    /**
     * 通过自定义用户认证服务查询获取用户信息
     *
     * @param token
     * @return
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
//        logger.info("获取token验证：" + token);
        if (!StringUtils.isEmpty(token)) {
            // 解析token获取用户名
            String username = jwtTokenUtil.getUsernameFromToken(token);
            if (username != null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//                logger.info("获取用户成功:" + JSON.toJSONString(userDetails));
                return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
            }
            return null;
        }
        return null;
    }
}
