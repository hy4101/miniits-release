package com.miniits.base.model.entity;

import com.miniits.base.mysql.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author: wq
 * @Date: 2018/4/3
 * @Time: 17:55
 * <p>
 * Description:
 * ***
 */
@Entity
@Table(name = "tag")
public class Tag extends BaseEntity {

    private String name;

    private Integer number;

    @Column(name = "name", length = 20, unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "number")
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Tag() {
    }

    public Tag(String name, Integer number) {
        this.name = name;
        this.number = number;
    }

    public Tag(String name) {
        this.name = name;
    }
}
