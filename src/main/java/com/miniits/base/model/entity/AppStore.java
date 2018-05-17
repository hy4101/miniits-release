package com.miniits.base.model.entity;

/**
 * @author: wq
 * @Date: 2018/5/16
 * @Time: 16:04
 * <p>
 * Description:
 * 页面、组件（DIY）应用市场
 */
public class AppStore {

    /**
     * 应用内容
     */
    private String appContent;

    /**
     * 作者id
     */
    private String authorId;

    /**
     * 作者名称
     */
    private String authorName;

    /**
     * 应用类型
     */
    private Integer appType;

    /**
     * 应用类型
     */
    private String appTypeName;

    /**
     * 备注
     */
    private String remark;

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

    public String getAppContent() {
        return appContent;
    }

    public void setAppContent(String appContent) {
        this.appContent = appContent;
    }
}
