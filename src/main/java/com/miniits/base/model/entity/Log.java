package com.miniits.base.model.entity;

import com.miniits.base.mysql.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by yjj on 2017/3/2.
 */
@Entity
@Table(name = "logs")
public class Log extends BaseEntity {

    @Column(name = "ip", length = 20)
    private String ip;

    @Column(name = "uri", length = 500)
    private String uri;

    @Column(name = "method", length = 500)
    private String method;

    @Column(name = "method_name", length = 500)
    private String methodName;

    @Column(name = "params", length = 1000)
    private String params;

    @Column(name = "module_name", length = 500)
    private String moduleName;


    private String country;


    private String region;


    private String city;

    @Column(name = "country", length = 100)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name = "region", length = 100)
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Column(name = "city", length = 100)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    @Override
    public String toString() {
        return "Log{" +
                ", ip='" + ip + '\'' +
                ", uri='" + uri + '\'' +
                ", method='" + method + '\'' +
                ", methodName='" + methodName + '\'' +
                ", params='" + params + '\'' +
                ", moduleName='" + moduleName + '\'' +
                '}';
    }
}
