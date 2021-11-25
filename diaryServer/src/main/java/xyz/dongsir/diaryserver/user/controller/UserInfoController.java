/*
 * FileName: UserInfoController
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2020 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package xyz.dongsir.diaryserver.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.dongsir.diaryserver.memorandum.vo.MemorandumOptionVO;
import xyz.dongsir.diaryserver.user.service.UserInfoService;
import xyz.dongsir.diaryserver.user.vo.UaseInfoOptionVO;
import xyz.dongsir.diaryserver.util.rest.ResultMsg;

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
}