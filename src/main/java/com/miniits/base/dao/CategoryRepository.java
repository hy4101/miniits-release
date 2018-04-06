package com.miniits.base.dao;

import com.miniits.base.model.entity.Category;
import com.miniits.base.mysql.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

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

    @Modifying
    @Query("update Category set number = number + (:number) where categoryName in (:categoryName)")
    void modifyCategoryNumber(@Param("categoryName") List<String> categoryName, @Param("number") Integer number);

}
