package xyz.dongsir.diaryserver.user.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Description: 操作用户VO
 *
 * @author dongxingyu
 * @version 2.0.0
 *
 * <p>
 * History:
 * Date                Author         Version     Description
 * --------------------------------------------------------------------
 * 2021/11/25 10:17     dongxingyu        2.0.0       To create
 * </p>
 */
public class UaseInfoOptionVO implements Serializable {

    @ApiModelProperty("账号")
    private String userAccount;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("用户名")
    private String userName;

    /** 属性名：手机号 **/
    @TableField("phone_")
    private String phone;

    /** 属性名：邮箱 **/
    @TableField("email_")
    private String email;

    /** 属性名：个人配置：存储Json **/
    @TableField("configuration_")
    private String configuration;

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }
}