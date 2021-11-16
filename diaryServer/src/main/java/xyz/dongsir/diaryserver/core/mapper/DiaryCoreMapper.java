/*
 * FileName: DiaryCoreMapper
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2020 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package xyz.dongsir.diaryserver.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
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
 * 2021/11/16 10:19     dongxingyu        2.0.0       To create
 * </p>
 */
@Mapper
public interface DiaryCoreMapper extends BaseMapper<DiaryCore> {
}