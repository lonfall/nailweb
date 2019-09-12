package com.lh.nailweb.config;

import com.google.common.collect.Lists;
import com.lh.nailweb.util.JwtTokenUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * @auther: loneyfall
 * @date: 2019/6/14
 * @description: swagger配置类
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).paths(PathSelectors.any()).build()
                .securityContexts(securityContexts()).securitySchemes(securitySchemes());
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("loneyfall", "", "loneyfall@gmail.com");
        return new ApiInfoBuilder()
                .title("nailweb接口")
                .description("基于SpringBoot快速开发平台")
                .version("1.0")
                .contact(contact)
                .build();
    }

    /**
     * 配置授权方案，这里在header中添加token
     *
     * @return
     */
    private List<ApiKey> securitySchemes() {
        return Lists.newArrayList(new ApiKey(jwtTokenUtil.getHeader(), jwtTokenUtil.getHeader(), "header"));
    }

    /**
     * 配置授权上下文
     *
     * @return
     */
    private List<SecurityContext> securityContexts() {
        SecurityContext context = SecurityContext.builder()
                .securityReferences(defaultAuth())
                //.forPaths(PathSelectors.regex("^(?!auth).*$"))
                .build();
        return Lists.newArrayList(context);
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(new SecurityReference(jwtTokenUtil.getHeader(), authorizationScopes));
    }
}
