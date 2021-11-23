/*
 * FileName: DocumentAddVO
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2020 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package xyz.dongsir.diaryserver.document.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Description: 添加文档VO
 *
 * @author dongxingyu
 * @version 2.0.0
 *
 * <p>
 * History:
 * Date                Author         Version     Description
 * --------------------------------------------------------------------
 * 2021/11/23 18:12     dongxingyu        2.0.0       To create
 * </p>
 */
public class DocumentAddVO implements Serializable {

    @ApiModelProperty("父节点id")
    private String parentId;

    @ApiModelProperty("标题")
    private String title;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}