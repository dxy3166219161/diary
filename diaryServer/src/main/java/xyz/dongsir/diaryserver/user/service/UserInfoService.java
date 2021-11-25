/*
 * FileName: UserInfoService
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2020 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package xyz.dongsir.diaryserver.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import xyz.dongsir.diaryserver.core.bean.DiaryCore;
import xyz.dongsir.diaryserver.user.bean.UserInfo;
import xyz.dongsir.diaryserver.user.vo.UaseInfoOptionVO;
import xyz.dongsir.diaryserver.util.rest.ResultMsg;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author dongxingyu
 * @version 2.0.0
 * <p>
 * History:
 * <p>
 * Date                Author         Version     Description
 * --------------------------------------------------------------------
 * 2021/11/25 9:51     dongxingyu        2.0.0       To create
 * </p>
 */
@Service
public interface UserInfoService extends IService<UserInfo> {
    ResultMsg<String> registerUser(UaseInfoOptionVO uaseInfoOptionVO);
}