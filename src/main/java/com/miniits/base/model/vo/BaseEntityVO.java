package com.miniits.base.model.vo;

import java.util.Date;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2017/12/27
 * @Time: 23:16
 * <p>
 * Description:
 * ***
 */

public class BaseEntityVO {

    private String id;

    private Date createDate;

    private String createBy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}
