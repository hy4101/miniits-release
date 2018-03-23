package com.miniits.base.model.vo;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/1/7
 * @Time: 16:31
 * <p>
 * Description:
 * ***
 */
public class UserVO extends BaseEntityVO {

    /**
     * 用户类型码
     */
    private Integer userTypeCode;

    /**
     * 用户类型
     */
    private String userTypeName;

    /**
     * 用户状态码
     */
    private Integer userStatusCode;

    /**
     * 用户状态
     */
    private String userStatusName;

    private String userName;

    private String password;

    private String EMail;

    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public Integer getUserStatusCode() {
        return userStatusCode;
    }

    public void setUserStatusCode(Integer userStatusCode) {
        this.userStatusCode = userStatusCode;
    }

    public String getUserStatusName() {
        return userStatusName;
    }

    public void setUserStatusName(String userStatusName) {
        this.userStatusName = userStatusName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEMail() {
        return EMail;
    }

    public void setEMail(String EMail) {
        this.EMail = EMail;
    }

    public Integer getUserTypeCode() {
        return userTypeCode;
    }

    public void setUserTypeCode(Integer userTypeCode) {
        this.userTypeCode = userTypeCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
