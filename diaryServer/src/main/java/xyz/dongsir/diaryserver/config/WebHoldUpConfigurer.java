package xyz.dongsir.diaryserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
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

    @Value("${web.url.whitelist}")
    private String whitelist;

    @Bean
    public HoldUpProcessConfig holdUpProcessConfig(){
        return new HoldUpProcessConfig();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册TestInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(holdUpProcessConfig());
        String[] whiteList = whitelist.split(",");
        for (String white : whiteList) {
            registration.excludePathPatterns(white);
        }
//        registration.excludePathPatterns(whitelist                       //添加不拦截路径
//                "你的登陆路径",            //登录
//                "/**/*.html",            //html静态资源
//                "/**/*.js",              //js静态资源
//                "/**/*.css"             //css静态资源
//        );
        registration.addPathPatterns("/**");                      //所有路径都被拦截
    }
}