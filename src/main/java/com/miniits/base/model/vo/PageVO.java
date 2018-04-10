package com.miniits.base.model.vo;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/3/24
 * @Time: 22:43
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
public class PageVO extends BaseEntityVO {

    private String pageName;

    private String pagePath;

    private Integer pageStatus;

    private String pageStatusName;

    private String pageAliasName;

    public String getPageAliasName() {
        return pageAliasName;
    }

    public void setPageAliasName(String pageAliasName) {
        this.pageAliasName = pageAliasName;
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
