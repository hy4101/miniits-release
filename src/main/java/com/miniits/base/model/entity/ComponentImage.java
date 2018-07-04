package com.miniits.base.model.entity;

import com.miniits.base.mysql.BaseEntity;

import javax.persistence.*;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/3/24
 * @Time: 11:11
 * <p>
 * 组件镜像表
 * WWW.MINIITS.COM
 */
@Entity
@Table(name = "component_image")
public class ComponentImage extends BaseEntity {

    private String componentReferenceId;

    private String componentName;

    private String componentId;

    private Integer componentType;

    private String componentTypeName;

    private Integer componentStatus;

    private String componentStatusName;

    private Integer componentSource;

    private String componentSourceName;

    private String componentBody;

    private String componentBodyApi;

    private Integer apiDataStructureType;

    private String dataFilters;

    private String objectKey;

    @Column(name = "component_name", nullable = false, length = 30)
    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    @Column(name = "component_id", nullable = false, unique = true, length = 45)
    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    @Column(name = "component_status", nullable = false, length = 9)
    public Integer getComponentStatus() {
        return componentStatus;
    }

    public void setComponentStatus(Integer componentStatus) {
        this.componentStatus = componentStatus;
    }

    @Column(name = "component_status_name", length = 25)
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

    @Column(name = "component_body_api", length = 500)
    public String getComponentBodyApi() {
        return componentBodyApi;
    }

    public void setComponentBodyApi(String componentBodyApi) {
        this.componentBodyApi = componentBodyApi;
    }

    @Column(name = "component_reference_id", length = 32)
    public String getComponentReferenceId() {
        return componentReferenceId;
    }

    public void setComponentReferenceId(String componentReferenceId) {
        this.componentReferenceId = componentReferenceId;
    }

    @Column(name = "component_type", length = 9)
    public Integer getComponentType() {
        return componentType;
    }

    public void setComponentType(Integer componentType) {
        this.componentType = componentType;
    }

    @Column(name = "component_type_name", length = 25)
    public String getComponentTypeName() {
        return componentTypeName;
    }

    public void setComponentTypeName(String componentTypeName) {
        this.componentTypeName = componentTypeName;
    }

    @Column(name = "data_filters", length = 500)
    public String getDataFilters() {
        return dataFilters;
    }

    public void setDataFilters(String dataFilters) {
        this.dataFilters = dataFilters;
    }

    @Column(name = "api_data_structure_type")
    public Integer getApiDataStructureType() {
        return apiDataStructureType;
    }

    public void setApiDataStructureType(Integer apiDataStructureType) {
        this.apiDataStructureType = apiDataStructureType;
    }

    @Column(name = "component_source", nullable = false, length = 9)
    public Integer getComponentSource() {
        return componentSource;
    }

    public void setComponentSource(Integer componentSource) {
        this.componentSource = componentSource;
    }

    @Column(name = "component_source_name", length = 25)
    public String getComponentSourceName() {
        return componentSourceName;
    }

    public void setComponentSourceName(String componentSourceName) {
        this.componentSourceName = componentSourceName;
    }

    @Column(name = "object_key", length = 100)
    public String getObjectKey() {
        return objectKey;
    }

    public void setObjectKey(String objectKey) {
        this.objectKey = objectKey;
    }
}
