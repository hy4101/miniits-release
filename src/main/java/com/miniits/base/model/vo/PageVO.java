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

    private Integer createStaticFile;

    private String keywords;

    private String description;

    private String title;

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

    public Integer getCreateStaticFile() {
        return createStaticFile;
    }

    public void setCreateStaticFile(Integer createStaticFile) {
        this.createStaticFile = createStaticFile;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
