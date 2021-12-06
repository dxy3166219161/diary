/*
 * FileName: WebHoldUpConfigurer
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2020 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package xyz.dongsir.diaryserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Description: 路由拦截配置
 *
 * @author dongxingyu
 * @version 2.0.0
 *
 * <p>
 * History:
 * Date                Author         Version     Description
 * --------------------------------------------------------------------
 * 2021/12/6 18:05     dongxingyu        2.0.0       To create
 * </p>
 */
@Configuration
public class WebHoldUpConfigurer implements WebMvcConfigurer {

    @Value("web.api.whitelist")
    private String whitelist;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册TestInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(new HoldUpProcessConfig());
        registration.addPathPatterns("/**");                      //所有路径都被拦截
        registration.excludePathPatterns(  whitelist                       //添加不拦截路径
//                "你的登陆路径",            //登录
//                "/**/*.html",            //html静态资源
//                "/**/*.js",              //js静态资源
//                "/**/*.css"             //css静态资源
        );
    }
}