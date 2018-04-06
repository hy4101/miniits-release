package com.miniits.base.model.entity;

import com.miniits.base.mysql.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/4/6
 * @Time: 17:19
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
@Entity
@Table(name = "links")
public class Links extends BaseEntity {

    private String url;

    private String linkName;

    private Integer status;

    private String statusName;


    @Column(name = "url",nullable = false,length = 320)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "link_name",nullable = false,length = 50)
    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "status_name",nullable = false,length = 50)
    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Links() {
    }

    public Links(String url, String linkName) {
        this.url = url;
        this.linkName = linkName;
    }

}
