package com.miniits.base.dao;

import com.miniits.base.model.entity.Category;
import com.miniits.base.mysql.BaseRepository;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/4/1
 * @Time: 21:46
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
public interface CategoryRepository extends BaseRepository<Category, String> {

    Category findByCategoryName(String categoryName);

}
