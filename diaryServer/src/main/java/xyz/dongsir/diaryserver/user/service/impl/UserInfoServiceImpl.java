package xyz.dongsir.diaryserver.user.service.impl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.dongsir.diaryserver.constants.UserConstant;
import xyz.dongsir.diaryserver.user.bean.UserInfo;
import xyz.dongsir.diaryserver.user.mapper.UserInfoMapper;
import xyz.dongsir.diaryserver.user.service.UserInfoService;
import xyz.dongsir.diaryserver.user.vo.LoginVO;
import xyz.dongsir.diaryserver.user.vo.UaseInfoOptionVO;
import xyz.dongsir.diaryserver.util.JwtTokenUtil;
import xyz.dongsir.diaryserver.util.RSAUtils;
import xyz.dongsir.diaryserver.util.SHA256Util;
import xyz.dongsir.diaryserver.util.UUIDUtil;
import xyz.dongsir.diaryserver.util.rest.ResponseMsg;
import xyz.dongsir.diaryserver.util.rest.ResultMsg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * 2021/11/25 9:52     dongxingyu        2.0.0       To create
 * </p>
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    RSAUtils rsaUtils;

    @Override
    public ResultMsg<String> registerUser(UaseInfoOptionVO uaseInfoOptionVO) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUid(UUIDUtil.getUUID());
        userInfo.setUserAccount(uaseInfoOptionVO.getUserAccount());
        userInfo.setUserName(uaseInfoOptionVO.getUserName());
        userInfo.setCreateDate(new Date());
        userInfo.setStatus(UserConstant.USER_STATUS_Y);
        userInfo.setType(UserConstant.USER_TYPE_USER);
        userInfo.setPhone(uaseInfoOptionVO.getPhone());
        userInfo.setEmail(uaseInfoOptionVO.getEmail());
        String password = UUIDUtil.getUUID();
        // 加密处理
        String encryptPassword = SHA256Util.getSHA256Str(password);
        userInfo.setPassword(encryptPassword);
        save(userInfo);
        return ResponseMsg.setSuccessResult("请记住密码：" + password);
    }

    @Override
    public ResultMsg<String> login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        StringBuilder tmp =  new StringBuilder();
        BufferedReader reader = null;
        try {
            InputStream inputStream = httpServletRequest.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line =reader.readLine()) != null) {
                tmp.append(line);
            }
            String userInfo = rsaUtils.decryptPrivateKey(tmp.toString());
            LoginVO loginVO = JSONObject.parseObject(userInfo, LoginVO.class);
            loginVO.setPassword(SHA256Util.getSHA256Str(loginVO.getPassword()));
            UserInfo user = userInfoMapper.findByUserAccountAndPassword(loginVO.getUserName(),loginVO.getPassword());
            if(null == user){
                return ResponseMsg.setErrorResult("登录失败，请重新登录");
            }
            Map<String,Object> map = new HashMap<>();
            map.put("userUid",user.getUid());
            map.put("userName",user.getUserName());
            map.put("userAccount",user.getUserAccount());
            map.put("phone",user.getPhone());
            map.put("email",user.getEmail());
            map.put("configuration",user.getConfiguration());
            String jwtToken = jwtTokenUtil.createJwtToken(user.getUid(), user.getUserName(), map);
            httpServletResponse.setHeader("Authorization",jwtToken);
            return ResponseMsg.setSuccessResult();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseMsg.setErrorResult("登录失败，请重新登录");
    }


}