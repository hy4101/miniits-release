package com.miniits.base.model.dto;

/**
 * @author: wq
 * @Date: 2018/5/23
 * @Time: 17:28
 * <p>
 * Description:
 * ***
 */
public class SeoDTO {

    /**
     * 页面或者文字id
     */
    private String id;

    private String keywords;

    private String description;

    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
