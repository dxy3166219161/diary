/*
 * FileName: CoreServiceTestImpl
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2020 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package xyz.dongsir.diaryserver.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import xyz.dongsir.diaryserver.core.bean.DiaryCore;

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