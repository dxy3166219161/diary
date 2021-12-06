/*
 * FileName: HoldUpProcessConfig
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2020 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package xyz.dongsir.diaryserver.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import xyz.dongsir.diaryserver.util.JwtTokenUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description: 路由拦截处理器
 *
 * @author dongxingyu
 * @version 2.0.0
 *
 * <p>
 * History:
 * Date                Author         Version     Description
 * --------------------------------------------------------------------
 * 2021/12/6 18:07     dongxingyu        2.0.0       To create
 * </p>
 */
public class HoldUpProcessConfig implements HandlerInterceptor {
    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        System.out.println("执行了TestInterceptor的preHandle方法");
//        try {
            //统一拦截（查询当前session是否存在user）(这里user会在每次登陆成功后，写入session)
//            User user=(User)request.getSession().getAttribute("USER");
//            if(user!=null){
//                return true;
//            }
//            response.sendRedirect(request.getContextPath()+"你的登陆页地址");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        // 校验token
        String authorization = request.getHeader("Authorization");
        return true;//如果设置为false时，被请求时，拦截器执行到此处将不会继续操作
        //如果设置为true时，请求将会继续执行后面的操作
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
//         System.out.println("执行了TestInterceptor的postHandle方法");
    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//        System.out.println("执行了TestInterceptor的afterCompletion方法");
    }
}