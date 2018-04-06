package com.miniits.base.model.vo;

import com.miniits.base.model.entity.Category;
import com.miniits.base.model.entity.Tag;

import java.util.List;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/4/6
 * @Time: 7:58
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
public class CategoryAndTag {

    private List<Category> categories;

    private List<Tag> tags;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public CategoryAndTag() {
    }

    public CategoryAndTag(List<Category> categories, List<Tag> tags) {
        this.categories = categories;
        this.tags = tags;
    }
}
