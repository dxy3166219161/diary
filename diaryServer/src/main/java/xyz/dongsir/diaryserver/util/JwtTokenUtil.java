package xyz.dongsir.diaryserver.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

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

//    public static void main(String[] args) {
//        System.out.printf(createJwtToken());
//    }

    public void laji(){
        this.ttl = 10000;
        this.key = "a2V5bmlkaWUxNTQ1NGRqc2Rqa3NqZA==";
    }
}