package com.miniits.base.model.entity;

import com.miniits.base.mysql.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "page")
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
}
