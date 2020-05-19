package com.lh.nailweb.config;

import com.lh.nailweb.security.filter.JwtAuthenticationFilter;
import com.lh.nailweb.security.filter.JwtLoginFilter;
import com.lh.nailweb.security.filter.MyDisableUrlSessionFilter;
import com.lh.nailweb.security.handler.MyAccessDeniedHandler;
import com.lh.nailweb.security.handler.MyAuthenticationFailureHandler;
import com.lh.nailweb.security.handler.MyAuthenticationSuccessHandler;
import com.lh.nailweb.security.handler.UnauthorizedEntryPointHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @EnableGlobalMethodSecurity 开启注解的权限控制，默认是关闭的。
 * prePostEnabled：使用表达式实现方法级别的控制，如：@PreAuthorize("hasRole('ADMIN')")
 * securedEnabled: 开启 @Secured 注解过滤权限，如：@Secured("ROLE_ADMIN")
 * jsr250Enabled: 开启 @RolesAllowed 注解过滤权限，如：@RolesAllowed("ROLE_ADMIN")
 * @auther: loneyfall
 * @date: 2019/6/8
 * @description: Spring Security 配置类
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService userDetailsService;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    private UnauthorizedEntryPointHandler unauthorizedEntryPointHandler;
    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;
    @Autowired
    private MyDisableUrlSessionFilter myDisableUrlSessionFilter;

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 在UsernamePasswordAuthenticationFilter之前添加jwt过滤器
//        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // 通过ApplicationContext注入，防止循环注入
        JwtLoginFilter jwtLoginFilter = this.getApplicationContext().getBean(JwtLoginFilter.class);
        JwtAuthenticationFilter jwtAuthenticationFilter = this.getApplicationContext().getBean(JwtAuthenticationFilter.class);
        // 添加jwt认证过滤器
        http.addFilter(jwtLoginFilter).addFilter(jwtAuthenticationFilter);
        // 用于去掉cookie中的jsessionid
        http.addFilterBefore(myDisableUrlSessionFilter, UsernamePasswordAuthenticationFilter.class);

        // 关闭CSRF跨域防护
        http.csrf().disable()
                // 关闭session创建
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                // 允许匿名的url
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/open-api/**").permitAll()
                .antMatchers("/api-docs", "/swagger-resources/**", "/swagger-ui.html**", "/webjars/**").permitAll()
                // 登录后可以访问任何请求
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/index").permitAll()
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler)
                .and().headers().cacheControl();

        // 处理异常情况：认证失败和权限不足
        http.exceptionHandling().authenticationEntryPoint(unauthorizedEntryPointHandler).accessDeniedHandler(myAccessDeniedHandler);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 设置拦截忽略文件夹，可以对静态资源放行
        web.ignoring().antMatchers("/static/**");
    }
}
