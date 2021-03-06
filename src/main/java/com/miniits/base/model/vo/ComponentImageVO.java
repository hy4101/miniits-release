package com.miniits.base.model.vo;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/3/24
 * @Time: 22:55
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
public class ComponentImageVO extends BaseEntityVO {

    private String componentName;

    private String componentId;

    private Integer componentType;

    private String componentTypeName;

    private Integer componentStatus;

    private String componentStatusName;

    private String componentBody;

    private String componentBodyApi;

    private Integer componentSource;

    private String componentSourceName;

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

    public Integer getComponentSource() {
        return componentSource;
    }

    public void setComponentSource(Integer componentSource) {
        this.componentSource = componentSource;
    }

    public String getComponentSourceName() {
        return componentSourceName;
    }

    public void setComponentSourceName(String componentSourceName) {
        this.componentSourceName = componentSourceName;
    }
}
