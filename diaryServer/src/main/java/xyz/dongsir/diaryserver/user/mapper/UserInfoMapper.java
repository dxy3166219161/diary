/*
 * FileName: UserInfoMapper
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2020 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package xyz.dongsir.diaryserver.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.dongsir.diaryserver.user.bean.UserInfo;

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
 * 2021/11/25 9:49     dongxingyu        2.0.0       To create
 * </p>
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}