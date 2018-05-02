package com.miniits.base.model.entity;

import com.miniits.base.mysql.BaseEntity;

import javax.persistence.*;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/3/24
 * @Time: 11:11
 * <p>
 * 组件表
 * WWW.MINIITS.COM
 */
@Entity
@Table(name = "component")
public class Component extends BaseEntity {

    @Column(name = "component_name", nullable = false, length = 30)
    private String componentName;

    @Column(name = "component_id", nullable = false, unique = true, length = 45)
    private String componentId;

    @Column(name = "component_Type", nullable = false, length = 9)
    private Integer componentType;

    @Column(name = "component_type_name", length = 25)
    private String componentTypeName;

    @Column(name = "component_status", nullable = false, length = 9)
    private Integer componentStatus;

    @Column(name = "component_status_name", length = 25)
    private String componentStatusName;

    private String componentBody;

    @Column(name = "component_body_api", nullable = false, length = 500)
    private String componentBodyApi;

    @Column(name = "data_filters", length = 500)
    private String dataFilters;

    //数据结构类型（对象/数组对象）
    @Column(name = "api_data_structure_type")
    private Integer apiDataStructureType;

    @Transient
    private String filters;

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public Integer getComponentStatus() {
        return componentStatus;
    }

    public void setComponentStatus(Integer componentStatus) {
        this.componentStatus = componentStatus;
    }

    public String getComponentStatusName() {
        return componentStatusName;
    }

    public void setComponentStatusName(String componentStatusName) {
        this.componentStatusName = componentStatusName;
    }

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "component_body", nullable = false, columnDefinition = "TEXT")
    public String getComponentBody() {
        return componentBody;
    }

    public void setComponentBody(String componentBody) {
        this.componentBody = componentBody;
    }

    public String getComponentBodyApi() {
        return componentBodyApi;
    }

    public void setComponentBodyApi(String componentBodyApi) {
        this.componentBodyApi = componentBodyApi;
    }

    public String getDataFilters() {
        return dataFilters;
    }

    public void setDataFilters(String dataFilters) {
        this.dataFilters = dataFilters;
    }

    public Integer getApiDataStructureType() {
        return apiDataStructureType;
    }

    public void setApiDataStructureType(Integer apiDataStructureType) {
        this.apiDataStructureType = apiDataStructureType;
    }

    @Transient
    public String getFilters() {
        return filters;
    }

    @Transient
    public void setFilters(String filters) {
        this.filters = filters;
    }

    public Integer getComponentType() {
        return componentType;
    }

    public void setComponentType(Integer componentType) {
        this.componentType = componentType;
    }

    public String getComponentTypeName() {
        return componentTypeName;
    }

    public void setComponentTypeName(String componentTypeName) {
        this.componentTypeName = componentTypeName;
    }
}
