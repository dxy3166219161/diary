package xyz.dongsir.diaryserver.user.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.dongsir.diaryserver.memorandum.vo.MemorandumOptionVO;
import xyz.dongsir.diaryserver.user.service.UserInfoService;
import xyz.dongsir.diaryserver.user.vo.LoginVO;
import xyz.dongsir.diaryserver.user.vo.UaseInfoOptionVO;
import xyz.dongsir.diaryserver.util.rest.ResponseMsg;
import xyz.dongsir.diaryserver.util.rest.ResultMsg;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
 * 2021/11/25 9:54     dongxingyu        2.0.0       To create
 * </p>
 */
@Api(tags = "用户管理模块")
@RestController
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;

    @ApiOperation(value = "注册")
    @PostMapping(value = "/register")
    public ResultMsg<String> registerUser(@RequestBody UaseInfoOptionVO uaseInfoOptionVO) {
        return userInfoService.registerUser(uaseInfoOptionVO);
    }

    @ApiOperation(value = "登录")
    @PostMapping(value = "/login")
    public ResultMsg<String> login(HttpServletRequest httpServletRequest) {
        return userInfoService.login(httpServletRequest);
    }
}