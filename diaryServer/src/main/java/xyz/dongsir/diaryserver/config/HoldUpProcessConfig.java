package xyz.dongsir.diaryserver.config;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import xyz.dongsir.diaryserver.util.JwtTokenUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

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

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 校验token
        String authorization = request.getHeader("Authorization");
        // 尝试解析，解析失败则退出
        try {
            jwtTokenUtil.parseJWT(authorization);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        if(jwtTokenUtil.overdueVerification(authorization)){
            // token续期  方案：检测到前端快过期，向请求头中置入一个新token字段
            Claims claims = jwtTokenUtil.parseJWT(authorization);
            Date now = new Date();
            long timeDifference = claims.getExpiration().getTime() - now.getTime();
            if(timeDifference < 3*60*1000 && timeDifference > 0 ){
                String refreshToken = jwtTokenUtil.refreshToken(authorization);
                response.setHeader("Authorization-Refresh",refreshToken);
            }
            return true;
        }
        return false;//如果设置为false时，被请求时，拦截器执行到此处将不会继续操作
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