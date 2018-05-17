package com.miniits.base.model.entity;

import com.miniits.base.mysql.BaseEntity;

import javax.persistence.*;

/**
 * @author: wq
 * @Date: 2018/5/16
 * @Time: 16:31
 * <p>
 * Description:
 * 应用内容-组件
 */
@Entity
@Table(name = "app_content")
public class AppContent extends BaseEntity {

    private AppStore appStore;

    private String componentName;

    private String componentId;

    private Integer componentType;

    private String componentTypeName;

    private Integer componentStatus;

    private String componentStatusName;

    private String componentBody;

    private String componentBodyApi;

    private String dataFilters;

    //数据结构类型（对象/数组对象）
    private Integer apiDataStructureType;

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

    @Column(name = "component_body_api", nullable = false, length = 500)
    public String getComponentBodyApi() {
        return componentBodyApi;
    }

    public void setComponentBodyApi(String componentBodyApi) {
        this.componentBodyApi = componentBodyApi;
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

    @Column(name = "component_Type", nullable = false, length = 9)
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

    @OneToOne(fetch = FetchType.LAZY)
    public AppStore getAppStore() {
        return appStore;
    }

    public void setAppStore(AppStore appStore) {
        this.appStore = appStore;
    }
}
