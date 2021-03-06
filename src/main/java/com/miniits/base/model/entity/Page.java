package com.miniits.base.model.entity;

import com.miniits.base.mysql.BaseEntity;

import javax.persistence.*;
import java.util.List;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/3/24
 * @Time: 11:06
 * <p>
 * 页面表
 * WWW.MINIITS.COM
 */
@Entity
@Table(name = "page")
public class Page extends BaseEntity {

    private List<PageComponentAssociate> pageComponentAssociates;

    private String pageName;

    private String pagePath;

    private Integer pageStatus;

    private String pageStatusName;

    private String pageAliasName;

    private Integer createStaticFile;

    private String keywords;

    private String description;

    private String title;

    @Column(name = "create_static_file", nullable = false)
    public Integer getCreateStaticFile() {
        return createStaticFile;
    }

    public void setCreateStaticFile(Integer createStaticFile) {
        this.createStaticFile = createStaticFile;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "page", cascade = {CascadeType.PERSIST})
    public List<PageComponentAssociate> getPageComponentAssociates() {
        return pageComponentAssociates;
    }

    public void setPageComponentAssociates(List<PageComponentAssociate> pageComponentAssociates) {
        this.pageComponentAssociates = pageComponentAssociates;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    public Integer getPageStatus() {
        return pageStatus;
    }

    public void setPageStatus(Integer pageStatus) {
        this.pageStatus = pageStatus;
    }

    public String getPageStatusName() {
        return pageStatusName;
    }

    public void setPageStatusName(String pageStatusName) {
        this.pageStatusName = pageStatusName;
    }

    public String getPageAliasName() {
        return pageAliasName;
    }

    public void setPageAliasName(String pageAliasName) {
        this.pageAliasName = pageAliasName;
    }

    @Column(name = "key_word",length = 500)
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Column(name = "description",length = 2000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "title",length = 500)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
