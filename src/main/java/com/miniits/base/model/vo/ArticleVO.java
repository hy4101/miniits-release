package com.miniits.base.model.vo;

import com.miniits.base.mysql.BaseEntity;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/3/24
 * @Time: 11:17
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
public class ArticleVO extends BaseEntity {
    /**
     * 路径
     */
    private Long path;
    /**
     * 内容状态
     */
    private Integer status;
    /**
     * 内容状态名称
     */
    private String statusName;
    /**
     * 内容来源
     */
    private Integer source;
    /**
     * 内容来源名称
     */
    private String sourceName;
    /**
     * 内容来源URL
     */
    private String sourceURL;
    /**
     * 内容标题名称
     */
    private String titleName;
    /**
     * 关键字
     */
    private String keys;
    /**
     * 内容标题图片
     */
    private String titleImage;
    /**
     * 内容作者id
     */
    private String authorId;
    /**
     * 内容作者名
     */
    private String authorName;
    /**
     * 内容类别
     */
    private String types;
    /**
     * 内容类别名称
     */
    private String typeNames;
    /**
     * 排序
     */
    private Integer sort;

    /**
     * 标签列表
     */
    private String tags;

    /**
     * 点击次数
     */
    private Integer hits;

    /**
     * 内容所属评论数
     */
    private Integer commentsNum;

    /**
     * 是否允许评论
     */
    private Integer allowComment;

    /**
     * 内容文字
     */
    private String content;

    /**
     * 内容文字
     * 用于列表展示的一部分
     */
    private String contentTitle;

    public Long getPath() {
        return path;
    }

    public void setPath(Long path) {
        this.path = path;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourceURL() {
        return sourceURL;
    }

    public void setSourceURL(String sourceURL) {
        this.sourceURL = sourceURL;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(String titleImage) {
        this.titleImage = titleImage;
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

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getTypeNames() {
        return typeNames;
    }

    public void setTypeNames(String typeNames) {
        this.typeNames = typeNames;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Integer getCommentsNum() {
        return commentsNum;
    }

    public void setCommentsNum(Integer commentsNum) {
        this.commentsNum = commentsNum;
    }

    public Integer getAllowComment() {
        return allowComment;
    }

    public void setAllowComment(Integer allowComment) {
        this.allowComment = allowComment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public ArticleVO() {
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }
}
