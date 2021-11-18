/*
 * FileName: DiaryCoreModel
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2020 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package xyz.dongsir.diaryserver.core.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Description: 核心数据树结构model
 *
 * @author dongxingyu
 * @version 2.0.0
 *
 * <p>
 * History:
 * Date                Author         Version     Description
 * --------------------------------------------------------------------
 * 2021/11/18 9:58     dongxingyu        2.0.0       To create
 * </p>
 */
public class DiaryCoreTreeModel implements Serializable {
    /** 属性名：主键 **/
    private Long id;

    /** 属性名：id uuid **/
    private String uid;

    /** 属性名：父级id uuid **/
    private String parentId;

    /** 属性名：标题 **/
    private String title;

    /** 属性名：类型：folder/documentation/年 文件夹/文件/年份/月份/日记 **/
    private String type;

    private List<DiaryCoreTreeModel> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<DiaryCoreTreeModel> getChildren() {
        return children;
    }

    public void setChildren(List<DiaryCoreTreeModel> children) {
        this.children = children;
    }
}