package com.lh.nailweb.security.filter;

import com.alibaba.fastjson.JSON;
import com.lh.nailweb.constant.Constant;
import com.lh.nailweb.entity.JwtUser;
import com.lh.nailweb.util.CookieUtils;
import com.lh.nailweb.util.JwtTokenUtil;
import com.lh.nailweb.util.MsgUtils;
import com.lh.nailweb.util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 设置该过滤器后AuthenticationSuccessHandler与AuthenticationFailureHandler将失效
 *
 * @auther: loneyfall
 * @date: 2019/6/12
 * @description: 登录认证过滤器
 */
@Component
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    private Logger logger = LoggerFactory.getLogger(JwtLoginFilter.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private AuthenticationManager authenticationManager;

    @Autowired
    public JwtLoginFilter(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
        this.authenticationManager = authenticationManager;
    }

    /**
     * 接收并解析用户凭证
     *
     * @param req
     * @param res
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        if (!req.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("不支持该授权方法: " + req.getMethod());
        }
        logger.info("登录认证过滤器——接收并解析用户凭证");
        String username = this.obtainUsername(req);
        String password = this.obtainPassword(req);
        JwtUser user = new JwtUser(username, password);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        return authenticationManager.authenticate(authentication);
    }

    /**
     * 用户成功登录后，这个方法会被调用，我们在这个方法里生成token
     *
     * @param req
     * @param res
     * @param chain
     * @param auth
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(auth);
        String token = jwtTokenUtil.generateToken(userDetails);

        logger.info("用户登录成功，生成token:" + token);

// 这里通过前端保存cookie
//        // 设置cookie
//        CookieUtils.writeCookie(res, jwtTokenUtil.getHeader(), token);

        res.addHeader(jwtTokenUtil.getHeader(), token);

        res.setContentType("application/json;charset=UTF-8");

        SecurityUtil.processRequest(req, res);

        ServletOutputStream out = res.getOutputStream();
        String str = JSON.toJSONString(MsgUtils.success(token));
        out.write(str.getBytes("UTF-8"));
        out.flush();
        out.close();
    }

    /**
     * 用户登录失败后，调用这个方法
     *
     * @param req
     * @param res
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest req,
                                              HttpServletResponse res,
                                              AuthenticationException failed) throws IOException, ServletException {
        logger.warn("用户登录失败:" + failed.getMessage());

        res.setContentType("application/json;charset=UTF-8");

        SecurityUtil.processRequest(req, res);

        ServletOutputStream out = res.getOutputStream();
        String str = JSON.toJSONString(MsgUtils.error(Constant.HTTP_UNAUTHORIZED, "登录认证失败！"));
        out.write(str.getBytes("UTF-8"));
        out.flush();
        out.close();
    }
}
