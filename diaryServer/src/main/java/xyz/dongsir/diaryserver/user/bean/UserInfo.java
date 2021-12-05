package xyz.dongsir.diaryserver.user.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * Description: 用户信息
 *
 * @author dongxingyu
 * @version 2.0.0
 *
 * <p>
 * History:
 * Date                Author         Version     Description
 * --------------------------------------------------------------------
 * 2021/11/25 9:42     dongxingyu        2.0.0       To create
 * </p>
 */
@TableName("user_info")
public class UserInfo {
    /** 属性名：主键 **/
    @TableId(value = "id_", type = IdType.AUTO)
    private Long id;

    /** 属性名：id uuid **/
    @TableField("uid_")
    private String uid;

    /** 属性名：用户账号 **/
    @TableField("user_account")
    private String userAccount;

    /** 属性名：密码 **/
    @TableField("password_")
    private String password;

    /** 属性名：用户名 **/
    @TableField("user_name")
    private String userName;

    /** 属性名：手机号 **/
    @TableField("phone_")
    private String phone;

    /** 属性名：邮箱 **/
    @TableField("email_")
    private String email;

    /** 属性名：创建时间 **/
    @TableField("create_date")
    private Date createDate;

    /** 属性名：修改时间 **/
    @TableField("update_date")
    private Date updateDate;

    /** 属性名：状态：Y/N 可使用/不可使用 **/
    @TableField("status_")
    private String status;

    /** 属性名：类型-超管：admin 普通用户：user **/
    @TableField("type_")
    private String type;

    /** 属性名：个人配置：存储Json **/
    @TableField("configuration_")
    private String configuration;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}