package com.miniits.base.model.vo;

import java.util.Date;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/6/29
 * @Time: 23:32
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
public class BaseEntityNotIdVO {

    private Date createDate;

    private String createBy;

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
