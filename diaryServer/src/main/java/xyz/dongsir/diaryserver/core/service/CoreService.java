/*
 * FileName: CoreServiceTestImpl
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2020 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package xyz.dongsir.diaryserver.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.dongsir.diaryserver.constants.GlobalConstant;
import xyz.dongsir.diaryserver.core.bean.DiaryCore;
//import xyz.dongsir.diaryserver.core.dao.CoreDao;
import xyz.dongsir.diaryserver.core.mapper.DiaryCoreMapper;
import xyz.dongsir.diaryserver.util.UUIDUtil;

import java.util.Date;

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
 * 2021/11/17 9:32     dongxingyu        2.0.0       To create
 * </p>
 */
@Service
public interface CoreService extends IService<DiaryCore> {
    void addCore(DiaryCore diaryCore);

    void updateCore(DiaryCore diaryCore);
}