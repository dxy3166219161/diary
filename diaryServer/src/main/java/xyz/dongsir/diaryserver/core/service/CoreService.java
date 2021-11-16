/*
 * FileName: CoreService
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2020 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package xyz.dongsir.diaryserver.core.service;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.dongsir.diaryserver.constants.GlobalConstant;
import xyz.dongsir.diaryserver.core.bean.DiaryCore;
import xyz.dongsir.diaryserver.core.mapper.DiaryCoreMapper;
import xyz.dongsir.diaryserver.util.UUIDUtil;

/**
 * Description: 核心服务
 *
 * @author dongxingyu
 * @version 2.0.0
 *
 * <p>
 * History:
 * Date                Author         Version     Description
 * --------------------------------------------------------------------
 * 2021/11/16 9:51     dongxingyu        2.0.0       To create
 * </p>
 */
@Service
public class CoreService {
    @Autowired
    DiaryCoreMapper diaryCoreMapper;

    /**
     * @description: 创建核心文档
     * @param: [diaryCore]
     * @return: void
     * @author dongxingyu
     * @date: 2021/11/16 10:20
     */
    public void addCore(DiaryCore diaryCore){
        String uuid = UUIDUtil.getUUID();
        diaryCore.setUid(uuid);
        // TODO 后续登录加入后补充逻辑，目前默认root
        diaryCore.setUserAccount("root");
        diaryCore.setUserName("root");
        diaryCore.setCreateDate(new Date());
        diaryCore.setStatus(GlobalConstant.STATUS_Y);
        // 存入数据库
        diaryCoreMapper.insert(diaryCore);
    }
}