package com.miniits.base.model.entity;

import com.miniits.base.mysql.BaseEntity;

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
public class PageComponentAssociate extends BaseEntity {

    private Page page;

    private ComponentImage componentImage;

    @Column(name = "sorts", length = 10)
    private Integer sorts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "page_id")
    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Integer getSorts() {
        return sorts;
    }

    public void setSorts(Integer sorts) {
        this.sorts = sorts;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "component_image_id")
    public ComponentImage getComponentImage() {
        return componentImage;
    }

    public void setComponentImage(ComponentImage componentImage) {
        this.componentImage = componentImage;
    }
}
