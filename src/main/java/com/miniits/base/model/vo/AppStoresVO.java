package com.miniits.base.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/6/21
 * @Time: 23:45
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
@JsonIgnoreProperties(ignoreUnknown = true, value = "id")
public class AppStoresVO {

    private String appName;

    /**
     * 系统编号，系统生成，唯一标识(m_001)
     */
    @JsonProperty(value = "sm")
    private String systemNumber;

    /**
     * 归属编号，开发者提供，唯一标识(m_001)
     */
    @JsonProperty(value = "am")
    private String ascriptionNumber;

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

    public String getSystemNumber() {
        return systemNumber;
    }

    public void setSystemNumber(String systemNumber) {
        this.systemNumber = systemNumber;
    }

    public String getAscriptionNumber() {
        return ascriptionNumber;
    }

    public void setAscriptionNumber(String ascriptionNumber) {
        this.ascriptionNumber = ascriptionNumber;
    }

    public String getAppImageUrl() {
        return appImageUrl;
    }

    public void setAppImageUrl(String appImageUrl) {
        this.appImageUrl = appImageUrl;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
