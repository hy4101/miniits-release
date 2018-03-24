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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "page")
    private List<Component> component;

    private String pageName;

    private String pagePath;

    private Integer pageStatus;

    private String pageStatusName;

}
