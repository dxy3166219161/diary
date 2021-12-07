package xyz.dongsir.diaryserver.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import xyz.dongsir.diaryserver.user.bean.UserInfo;
import xyz.dongsir.diaryserver.user.service.impl.UserInfoServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * Description:
 *
 * @author dongxingyu
 * @version 2.0.0
 *
 * <p>
 * History:
 * Date                Author         Version     Description
 * --------------------------------------------------------------------
 * 2021/11/26 16:36     dongxingyu        2.0.0       To create
 * </p>
 */
@Component
@ConfigurationProperties("jwt.config")
@Data
public class JwtTokenUtil {
    private String key ;

    private long ttl ;

    @Autowired
    UserInfoServiceImpl userInfoService;

    /** 
     * @description: id：数据库id subject:用户账号 map:token中存储的自定义信息
     * @param: [id, subject, map] 
     * @return: java.lang.String
     * @author dongxingyu
     * @date: 2021/12/5 18:45
     */ 
    public String createJwtToken(String id, String subject,Map<String,Object> map){
        //1.设置失效时间
        long now=System.currentTimeMillis();//当前毫秒数
        long exp=now+ttl;
        //2.创建jwtBuilder
        JwtBuilder jwtBuilder = Jwts.builder().setId(id).setSubject(subject)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, key);

        //3.通过map设置claims,指定失效时间
        for (Map.Entry<String,Object> entry:map.entrySet()){
            jwtBuilder.claim(entry.getKey(),entry.getValue());
        }
        //jwtBuilder.setClaims(map);
        jwtBuilder.setExpiration(new Date(exp));
        //4.创建token
        String token = jwtBuilder.compact();
        return token;
    }

    public Claims parseJWT(String token){
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        return claims;
    }

    /**
     * @description: 校验token是否过期
     * @param: [token]
     * @return: boolean true：未过期 false:过期
     * @author dongxingyu
     * @date: 2021/12/7 10:04
     */
    public boolean overdueVerification(String token){
        try {
            Claims claims = parseJWT(token);
            Date now = new Date();
            if(claims.getExpiration().after(now)){
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    /**
     * @description: 获取当前用户信息
     * @param: []
     * @return: xyz.dongsir.diaryserver.user.bean.UserInfo
     * @author dongxingyu
     * @date: 2021/12/7 10:45
     */
    public UserInfo getCurrentUser(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        String authorization = request.getHeader("Authorization");
        Claims claims = parseJWT(authorization);
        String subject = claims.getSubject();
        UserInfo userInfo = userInfoService.getById(subject);
        return userInfo;
    }

//    public static void main(String[] args) {
//        System.out.printf(createJwtToken());
//    }

    /** 
     * @description: 刷新token
     * @param: [token] 
     * @return: java.lang.String 
     * @author dongxingyu
     * @date: 2021/12/7 10:49
     */ 
    public String refreshToken(String token){
        Claims claims = parseJWT(token);
        long now=System.currentTimeMillis();//当前毫秒数
        long exp=now+ttl;
        claims.setExpiration(new Date(exp));
        JwtBuilder jwtBuilder = Jwts.builder().setClaims(claims);
        token = jwtBuilder.compact();
        return token;
    }

    public void laji(){
        this.ttl = 1000000000;
        this.key = "a2V5bmlkaWUxNTQ1NGRqc2Rqa3NqZA==";
    }
}