package com.miniits.base.model.entity;

import com.miniits.base.mysql.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/4/20
 * @Time: 22:41
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
@Entity
@Table(name = "image")
public class Image extends BaseEntity {

    private String name;

    private String url;

    private String addressUrl;

    private String html;

    private String BBCode;

    private String markdown;

    @Column(name = "address_url", length = 300, nullable = false)
    public String getAddressUrl() {
        return addressUrl;
    }

    public void setAddressUrl(String addressUrl) {
        this.addressUrl = addressUrl;
    }

    @Column(name = "name", length = 60, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "url", length = 500, nullable = false)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "html", length = 500)
    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    @Column(name = "bb_code", length = 500)
    public String getBBCode() {
        return BBCode;
    }

    public void setBBCode(String BBCode) {
        this.BBCode = BBCode;
    }

    @Column(name = "markdown", length = 500)
    public String getMarkdown() {
        return markdown;
    }

    public void setMarkdown(String markdown) {
        this.markdown = markdown;
    }
}
