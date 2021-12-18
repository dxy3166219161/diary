package xyz.dongsir.diaryserver.api.vo;

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
 * 2021/12/18 10:39     dongxingyu        2.0.0       To create
 * </p>
 */
public class WebInterfaceAccessVO implements Serializable {
    private String userAccount;

    private String password;

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
}