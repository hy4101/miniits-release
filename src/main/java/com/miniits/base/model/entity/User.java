package com.miniits.base.model.entity;


import com.miniits.base.mysql.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/1/2
 * @Time: 23:37
 * <p>
 * Description:
 * ***
 */
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    /**
     * 用户类型码
     */
    @Column(name = "user_type_code", nullable = false)
    private Integer userTypeCode;

    /**
     * 用户类型
     */
    @Size(max = 10)
    @Column(name = "user_type_name", nullable = false)
    private String userTypeName;

    /**
     * 用户状态码
     */
    @Column(name = "user_status_code", nullable = false)
    private Integer userStatusCode;

    /**
     * 用户状态
     */
    @Size(max = 10)
    @Column(name = "user_status_name", nullable = false)
    private String userStatusName;

    @Size(max = 60)
    @Column(name = "user_name", nullable = false)
    private String userName;

    @Size(max = 60)
    @Column(name = "email", nullable = false)
    private String email;

    @Size(max = 120)
    @Column(name = "pass_word", nullable = false)
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserTypeCode() {
        return userTypeCode;
    }

    public void setUserTypeCode(Integer userTypeCode) {
        this.userTypeCode = userTypeCode;
    }

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

}
