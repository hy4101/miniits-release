package com.miniits.base.model.entity;

import javax.persistence.*;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/3/24
 * @Time: 11:11
 * <p>
 * 组件表
 * WWW.MINIITS.COM
 */
public class Component {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "page_id")
    private Page page;

    @Column(name = "component_name", nullable = false, length = 30)
    private String componentName;

    @Column(name = "component_id", nullable = false, length = 125)
    private String componentId;

    @Column(name = "component_status", nullable = false, length = 9)
    private Integer componentStatus;

    @Column(name = "component_status_name", length = 25)
    private String componentStatusName;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "component_body", nullable = false, columnDefinition = "TEXT")
    private String componentBody;

    @Column(name = "component_body_api", nullable = false, length = 500)
    private String componentBodyApi;

}
