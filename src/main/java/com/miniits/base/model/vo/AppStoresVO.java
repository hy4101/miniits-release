package com.miniits.base.model.vo;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/6/21
 * @Time: 23:45
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
public class AppStoresVO {

    /**
     * 编号，唯一标识(m_001)
     */
    private String number;

    /**
     * 应用图片url
     */
    private String appImageUrl;

    /**
     * 作者名称
     */
    private String authorName;

    /**
     * 应用类型
     */
    private Integer appType;

    /**
     * 应用类型名称
     */
    private String appTypeName;

    /**
     * 下载次数
     */
    private Integer downloadNumber;

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

    public Integer getDownloadNumber() {
        return downloadNumber;
    }

    public void setDownloadNumber(Integer downloadNumber) {
        this.downloadNumber = downloadNumber;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAppImageUrl() {
        return appImageUrl;
    }

    public void setAppImageUrl(String appImageUrl) {
        this.appImageUrl = appImageUrl;
    }
}
