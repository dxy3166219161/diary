/*
 * FileName: CoreDataConstant
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2020 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package xyz.dongsir.diaryserver.constants;

import java.io.Serializable;

/**
 * Description: 核心数据常量
 *
 * @author dongxingyu
 * @version 2.0.0
 *
 * <p>
 * History:
 * Date                Author         Version     Description
 * --------------------------------------------------------------------
 * 2021/11/16 10:04     dongxingyu        2.0.0       To create
 * </p>
 */
public class CoreDataConstant implements Serializable {
    /** 属性名： 核心数据根id - 日记 **/
    public static final String CORE_DATA_PARENT_FOOT_ID_DIRAY = "0";

    /** 属性名： 核心数据根id - 文档 **/
    public static final String CORE_DATA_PARENT_FOOT_ID_DOCUMENT = "1";

    /** 属性名： 核心数据根id - 备忘录 **/
    public static final String CORE_DATA_PARENT_FOOT_ID_MEMORANDUM = "2";
}