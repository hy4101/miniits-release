package com.miniits.base.model.entity;

import com.miniits.base.mysql.BaseEntity;

import javax.persistence.*;
import java.util.List;

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
     * 分类
     */
    private List<ArticleCatrgoryAssociate> catrgoryAssociates;
    /**
     * 路径
     */
    private Long path = System.currentTimeMillis();

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

    @Column(name = "path")
    public Long getPath() {
        return path;
    }

    public void setPath(Long path) {
        this.path = path;
    }

    @Column(name = "status", length = 9, nullable = false)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "status_name", length = 30)
    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Column(name = "source", length = 9, nullable = false)
    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    @Column(name = "source_name", length = 30)
    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    @Column(name = "source_url", length = 1000)
    public String getSourceURL() {
        return sourceURL;
    }

    public void setSourceURL(String sourceURL) {
        this.sourceURL = sourceURL;
    }

    @Column(name = "title_name", length = 125, nullable = false)
    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    @Column(name = "title_image", length = 1000)
    public String getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(String titleImage) {
        this.titleImage = titleImage;
    }

    @Column(name = "author_id", length = 50)
    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    @Column(name = "author_name", length = 50)
    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Column(name = "types", length = 150)
    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    @Column(name = "type_names", length = 225)
    public String getTypeNames() {
        return typeNames;
    }

    public void setTypeNames(String typeNames) {
        this.typeNames = typeNames;
    }

    @Column(name = "sort")
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Column(name = "tags")
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Column(name = "hits")
    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    @Column(name = "comments_num")
    public Integer getCommentsNum() {
        return commentsNum;
    }

    public void setCommentsNum(Integer commentsNum) {
        this.commentsNum = commentsNum;
    }

    @Column(name = "allow_comment")
    public Integer getAllowComment() {
        return allowComment;
    }

    public void setAllowComment(Integer allowComment) {
        this.allowComment = allowComment;
    }

    @Lob
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "content_title", length = 125)
    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public Article() {
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "articles", cascade = {CascadeType.PERSIST})
    public List<ArticleCatrgoryAssociate> getCatrgoryAssociates() {
        return catrgoryAssociates;
    }

    public void setCatrgoryAssociates(List<ArticleCatrgoryAssociate> catrgoryAssociates) {
        this.catrgoryAssociates = catrgoryAssociates;
    }
}
