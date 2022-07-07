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

//    @Test
//    void contextLoads() {
//        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
//        jwtTokenUtil.laji();
//        Map<String, Object> map = new HashMap<>();
//        map.put("qwe","qwe");
//        String jwtToken = jwtTokenUtil.createJwtToken("1", "dongxingyu", map);
//        System.out.println(jwtToken);
//
//        //
//    }
//
//    @Test
//    void analysisToken() {
//        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
//        jwtTokenUtil.laji();
//        String token = "eyJhbGciOiJSUzI1NiJ9.eyJqdGkiOiIxIiwic3ViIjoiZG9uZ3hpbmd5dSIsImlhdCI6MTY0MDM5ODY2MywicXdlIjoicXdlIiwiZXhwIjoxNjQxMzk4NjYzfQ.V8Y-DSQWD2yr2aPeTreQ4HUT0ZaRqI2-iVnZ-5E57peQuF78-2T_B4KGbAnzliyAfGNJu3wPlfUVzOqD_mRtaavqB2ewvShqfhyQZm9SenOQb23n7AxvIS8SBu3d41yjApg-XgxBBhSjVN-d4EdmTMUi3mdHcCBFANdWfivbQMvprm0Vj2c1XZVuW1d4mixgXR1dO5NnDEgHxlMdXi4XB5ZBescmjARjAR1NptusG3xTpZl5yBkYMVWVYm1BqEwRfe6LuudL2ss8at0DG6KuMI7uGUFjQX-VN4N4xqSB4GnIHIXWxU-P60c6OxWA6WsnsYzfYxtXiCFystwThx0IGA\n";
//        Claims claims = jwtTokenUtil.parseJWT(token);
//        System.out.println(JSONObject.toJSONString(claims));
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//
//        System.out.println("签发时间:"+sdf.format(claims.getIssuedAt()));
//        System.out.println("过期时间:"+sdf.format(claims.getExpiration()));
//        System.out.println("当前时间:"+sdf.format(new Date()) );
//        Date now = new Date();
//        if(claims.getExpiration().after(now)){
//            System.out.println("1");
//        }
//    }


}
