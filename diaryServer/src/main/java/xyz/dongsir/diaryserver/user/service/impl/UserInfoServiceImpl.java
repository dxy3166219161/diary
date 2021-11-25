/*
 * FileName: UserInfoServiceImpl
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2020 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package xyz.dongsir.diaryserver.user.service.impl;
import java.util.Date;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.dongsir.diaryserver.constants.UserConstant;
import xyz.dongsir.diaryserver.core.bean.DiaryCore;
import xyz.dongsir.diaryserver.core.mapper.DiaryCoreMapper;
import xyz.dongsir.diaryserver.user.bean.UserInfo;
import xyz.dongsir.diaryserver.user.mapper.UserInfoMapper;
import xyz.dongsir.diaryserver.user.service.UserInfoService;
import xyz.dongsir.diaryserver.user.vo.UaseInfoOptionVO;
import xyz.dongsir.diaryserver.util.UUIDUtil;
import xyz.dongsir.diaryserver.util.rest.ResponseMsg;
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
 * 2021/11/25 9:52     dongxingyu        2.0.0       To create
 * </p>
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    @Override
    public ResultMsg<String> registerUser(UaseInfoOptionVO uaseInfoOptionVO) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUid(UUIDUtil.getUUID());
        userInfo.setUserAccount(uaseInfoOptionVO.getUserAccount());
        userInfo.setUserName(uaseInfoOptionVO.getUserName());
        userInfo.setCreateDate(new Date());
        userInfo.setStatus(UserConstant.USER_STATUS_Y);
        userInfo.setType(UserConstant.USER_TYPE_USER);
        userInfo.setPhone(userInfo.getPhone());
        userInfo.setEmail(userInfo.getEmail());
        String password = UUIDUtil.getUUID();
        // TODO 后续追加加密处理
        String encryptPassword = password;
        userInfo.setPassword(encryptPassword);
        save(userInfo);
        return ResponseMsg.setSuccessResult("请记住密码：" + password);
    }
}