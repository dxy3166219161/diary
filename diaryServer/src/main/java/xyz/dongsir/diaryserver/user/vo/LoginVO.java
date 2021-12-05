package xyz.dongsir.diaryserver.user.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

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
 * 2021/11/26 15:30     dongxingyu        2.0.0       To create
 * </p>
 */
public class LoginVO implements Serializable {

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("密码")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}