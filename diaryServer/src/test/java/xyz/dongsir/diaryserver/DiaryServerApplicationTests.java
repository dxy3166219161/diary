package xyz.dongsir.diaryserver;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.dongsir.diaryserver.util.JwtTokenUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class DiaryServerApplicationTests {

    @Test
    void contextLoads() {
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        jwtTokenUtil.laji();
        Map<String, Object> map = new HashMap<>();
        map.put("qwe","qwe");
        String jwtToken = jwtTokenUtil.createJwtToken("1", "dongxingyu", map);
        System.out.println(jwtToken);

        //
    }

    @Test
    void analysisToken() {
        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        jwtTokenUtil.laji();
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoiZG9uZ3hpbmd5dSIsImlhdCI6MTYzODg0MDQ3MiwicXdlIjoicXdlIiwiZXhwIjoxNjM5ODQwNDcyfQ.9st1MegJTMqBeAqYsCgm4rMW0a6u-Mcr8O2TySF7leA";
        Claims claims = jwtTokenUtil.parseJWT(token);
        System.out.println(JSONObject.toJSONString(claims));
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        System.out.println("签发时间:"+sdf.format(claims.getIssuedAt()));
        System.out.println("过期时间:"+sdf.format(claims.getExpiration()));
        System.out.println("当前时间:"+sdf.format(new Date()) );
        Date now = new Date();
        if(claims.getExpiration().after(now)){
            System.out.println("1");
        }
    }


}
