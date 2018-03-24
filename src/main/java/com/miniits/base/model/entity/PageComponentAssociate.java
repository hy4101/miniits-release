package com.miniits.base.model.entity;

import javax.persistence.*;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/3/24
 * @Time: 11:10
 * <p>
 * 页面组件关联表
 * WWW.MINIITS.COM
 */
@Entity
@Table(name = "page_component_associate")
public class PageComponentAssociate {

    private Page page;

    private Component component;

    @Column(name = "sort", length = 10)
    private Integer sort;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "page_id")
    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "component_id")
    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }
}
