package com.miniits.base.model.entity;

import com.miniits.base.mysql.BaseEntity;

import javax.persistence.*;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/3/24
 * @Time: 11:17
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
@Entity
@Table(name = "article")
public class Article extends BaseEntity {
    /**
     * post表主键
     */
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "path")
    private Integer path;

    /**
     * 内容状态
     */
    @Column(name = "status", length = 9, nullable = false)
    private Integer status;

    /**
     * 内容状态名称
     */
    @Column(name = "status_name", length = 30)
    private String statusName;

    /**
     * 内容来源
     */
    @Column(name = "source", length = 9, nullable = false)
    private Integer source;

    /**
     * 内容来源名称
     */
    @Column(name = "source_name", length = 30)
    private String sourceName;

    /**
     * 内容来源URL
     */
    @Column(name = "source_url", length = 1000)
    private String sourceURL;

    /**
     * 内容标题名称
     */
    @Column(name = "title_name", length = 125, nullable = false)
    private String titleName;

    /**
     * 内容标题图片
     */
    @Column(name = "title_image", length = 1000)
    private String titleImage;

    /**
     * 内容作者id
     */
    @Column(name = "author_id", length = 50)
    private Integer authorId;

    /**
     * 内容作者名
     */
    @Column(name = "author_name", length = 50)
    private String authorName;

    /**
     * 内容类别
     */
    @Column(name = "types", length = 150)
    private String types;

    /**
     * 内容类别名称
     */
    @Column(name = "type_names", length = 225)
    private String typeNames;
    /**
     * 排序
     */
    @Column(name = "sort")
    private Integer sort;

    /**
     * 标签列表
     */
    @Column(name = "tags")
    private String tags;

    /**
     * 点击次数
     */
    @Column(name = "hits")
    private Integer hits;

    /**
     * 内容所属评论数
     */
    @Column(name = "comments_num")
    private Integer commentsNum;

    /**
     * 是否允许评论
     */
    @Column(name = "allow_comment")
    private Boolean allowComment;

    /**
     * 内容文字
     */
    @Lob
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    /**
     * 内容文字
     * 用于列表展示的一部分
     */
    @Column(name = "content_title", length = 125)
    private String contentTitle;

    public Integer getPath() {
        return path;
    }

    public void setPath(Integer path) {
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

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
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

    public Boolean getAllowComment() {
        return allowComment;
    }

    public void setAllowComment(Boolean allowComment) {
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
}
