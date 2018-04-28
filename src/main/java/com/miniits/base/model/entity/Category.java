package com.miniits.base.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.miniits.base.mysql.BaseEntity;

import javax.persistence.*;
import java.util.List;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/4/1
 * @Time: 21:17
 * <p>
 * Description:
 * WWW.MINIITS.COM
 * 分类/类别
 */
@Entity
@Table(name = "category")
public class Category extends BaseEntity {

    @Column(name = "category_name", unique = true, nullable = false, length = 30)
    private String categoryName;

    @Column(name = "p_id", length = 36)
    private String pId;

    @Column(name = "level", nullable = false)
    private Integer level;

    @Column(name = "number")
    private Integer number;

    private List<ArticleCatrgoryAssociate> articleCatrgoryAssociates;

    @Column(name = "category_name", length = 45, unique = true, nullable = false)
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categories", cascade = {CascadeType.PERSIST})
    @JsonIgnore
    public List<ArticleCatrgoryAssociate> getArticleCatrgoryAssociates() {
        return articleCatrgoryAssociates;
    }

    public void setArticleCatrgoryAssociates(List<ArticleCatrgoryAssociate> articleCatrgoryAssociates) {
        this.articleCatrgoryAssociates = articleCatrgoryAssociates;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
