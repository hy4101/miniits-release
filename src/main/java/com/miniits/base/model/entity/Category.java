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
 */
@Entity
@Table(name = "category")
public class Category extends BaseEntity {

    private String categoryName;

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

    public Category() {
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
}
