package xyz.dongsir.diaryserver.core.bean;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

/**
 * Description: 日记核心
 *
 * @author dongxingyu
 * @version 2.0.0
 *
 * <p>
 * History:
 * Date                Author         Version     Description
 * --------------------------------------------------------------------
 * 2021/11/15 10:48     dongxingyu        2.0.0       To create
 * </p>
 */
@TableName("diary_core")
public class DiaryCore {
    /** 属性名：主键 **/
    @TableId(value = "id_", type = IdType.AUTO)
    private Long id;

    /** 属性名：id uuid **/
    @TableField("uid_")
    private String uid;

    /** 属性名：用户账号 **/
    @TableField("user_account")
    private String userAccount;

    /** 属性名：用户名 **/
    @TableField("user_name")
    private String userName;

    /** 属性名：父级id uuid **/
    @TableField("parent_id")
    private String parentId;

    /** 属性名：创建时间 **/
    @TableField("create_date")
    private Date createDate;

    /** 属性名：修改时间 **/
    @TableField("update_date")
    private Date updateDate;

    /** 属性名：标题 **/
    @TableField("title_")
    private String title;

    /** 属性名：文档摘要 **/
    @TableField("summary_")
    private String summary;

    /** 属性名：详情：存储Json **/
    @TableField("detail_")
    private String detail;

    /** 属性名：类型：folder/documentation/年 文件夹/文件/年份/月份/日记 **/
    @TableField("type_")
    private String type;

    /** 属性名：状态：Y/N 可使用/不可使用 **/
    @TableField("status_")
    private String status;

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

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}