package com.miniits.base.model.dto;

import com.miniits.base.model.entity.Component;

import java.util.Date;

/**
 * @author: wq
 * @Date: 2018/5/16
 * @Time: 16:53
 * <p>
 * Description:
 * ***
 */
public class AppPushDTO {

    private Component component;

    private String authorId;

    private String authorName;

    private Integer appType;

    private String appTypeName;

    private String remark;

    private Date upTime;

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public String getAppTypeName() {
        return appTypeName;
    }

    public void setAppTypeName(String appTypeName) {
        this.appTypeName = appTypeName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }
}
