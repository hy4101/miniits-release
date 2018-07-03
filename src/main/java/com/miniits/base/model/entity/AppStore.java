package com.miniits.base.model.entity;

import com.miniits.base.mysql.BaseEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: wq
 * @Date: 2018/5/16
 * @Time: 16:04
 * <p>
 * Description:
 * 页面、组件（DIY）应用市场
 */
@Entity
@Table(name = "app_store")
public class AppStore extends BaseEntity {

    /**
     * 应用内容
     */
    private AppContent appContent;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 系统编号，系统生成，唯一标识(m_001)
     */
    private String systemNumber;

    /**
     * 归属编号，开发者提供，唯一标识(m_001)
     */
    private String ascriptionNumber;

    /**
     * 应用图片url
     */
    private String appImageUrl;

    /**
     * 作者id
     */
//    private String authorId;

    /**
     * 作者名称
     */
    private String authorName;

    /**
     * 应用状态
     */
    private Integer appStatus;

    /**
     * 应用状态名称
     */
    private String appStatusName;

    /**
     * 应用类型
     */
    private Integer appType;

    /**
     * 应用类型
     */
    private String appTypeName;

    /**
     * 上架时间
     */
    private Date upTime;

    /**
     * 下架时间
     */
    private Date downTime;

    /**
     * 下载次数
     */
    private Integer downloadNumber;

    /**
     * 被收藏数
     */
    private Integer collectionNumber;

    /**
     * 被点赞数
     */
    private Integer starNumber;

    /**
     * 标签
     */
    private String tags;

    /**
     * 备注
     */
    private String remark;

//    @Column(name = "author_id", nullable = false, length = 36)
//    public String getAuthorId() {
//        return authorId;
//    }
//
//    public void setAuthorId(String authorId) {
//        this.authorId = authorId;
//    }

    @Column(name = "author_name", length = 50)
    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Column(name = "app_status", nullable = false, length = 9)
    public Integer getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(Integer appStatus) {
        this.appStatus = appStatus;
    }

    @Column(name = "app_status_name", length = 30)
    public String getAppStatusName() {
        return appStatusName;
    }

    public void setAppStatusName(String appStatusName) {
        this.appStatusName = appStatusName;
    }

    @Column(name = "app_type", nullable = false, length = 9)
    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    @Column(name = "app_type_name", length = 30)
    public String getAppTypeName() {
        return appTypeName;
    }

    public void setAppTypeName(String appTypeName) {
        this.appTypeName = appTypeName;
    }

    @Column(name = "up_time", nullable = false)
    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    @Column(name = "down_time")
    public Date getDownTime() {
        return downTime;
    }

    public void setDownTime(Date downTime) {
        this.downTime = downTime;
    }

    @Column(name = "download_number")
    public Integer getDownloadNumber() {
        return downloadNumber;
    }

    public void setDownloadNumber(Integer downloadNumber) {
        this.downloadNumber = downloadNumber;
    }

    @Column(name = "collection_number")
    public Integer getCollectionNumber() {
        return collectionNumber;
    }

    public void setCollectionNumber(Integer collectionNumber) {
        this.collectionNumber = collectionNumber;
    }

    @Column(name = "star_number")
    public Integer getStarNumber() {
        return starNumber;
    }

    public void setStarNumber(Integer starNumber) {
        this.starNumber = starNumber;
    }

    @Column(name = "remark", length = 500)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "app_content_id")
    public AppContent getAppContent() {
        return appContent;
    }

    public void setAppContent(AppContent appContent) {
        this.appContent = appContent;
    }

    @Column(name = "system_number", length = 100, unique = true, nullable = false)
    public String getSystemNumber() {
        return systemNumber;
    }

    public void setSystemNumber(String systemNumber) {
        this.systemNumber = systemNumber;
    }

    @Column(name = "ascription_number", length = 100, unique = true, nullable = false)
    public String getAscriptionNumber() {
        return ascriptionNumber;
    }

    public void setAscriptionNumber(String ascriptionNumber) {
        this.ascriptionNumber = ascriptionNumber;
    }

    @Column(name = "app_image_url", length = 1000, nullable = false)
    public String getAppImageUrl() {
        return appImageUrl;
    }

    public void setAppImageUrl(String appImageUrl) {
        this.appImageUrl = appImageUrl;
    }

    @Column(name = "tags", length = 500)
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Column(name = "app_name", length = 100)
    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
}
