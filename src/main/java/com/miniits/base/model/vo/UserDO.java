package com.miniits.base.model.vo;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/1/7
 * @Time: 16:31
 * <p>
 * Description:
 * ***
 */
public class UserDO extends BaseEntityVO {

    private String password;

    private String userName;

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
}
