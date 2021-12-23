package xyz.dongsir.diaryserver.config;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import xyz.dongsir.diaryserver.util.JwtTokenUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
    private static Logger logger = LoggerFactory.getLogger(HoldUpProcessConfig.class);

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
        if(StringUtils.isBlank(authorization)){
            logger.info("拦截url--{}",request.getRequestURI());
            returnMsg(response,500,"请携带token",false);
            return false;
        }
        try {
            jwtTokenUtil.parseJWT(authorization);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("token解析异常url--{}",request.getRequestURI());
            returnMsg(response,401,"token解析异常",false);
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
//            returnMsg(response,200,"",true);
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

    private void returnMsg(HttpServletResponse response,Integer code,String msg,boolean success){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        JSONObject res = new JSONObject();
        res.put("data", success);
        res.put("code", code);
        res.put("msg", msg);
        try {
            out = response.getWriter();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        out.append(res.toString());
    }
}