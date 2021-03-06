package com.miniits.base.dao;

import com.miniits.base.model.entity.Article;
import com.miniits.base.mysql.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/3/28
 * @Time: 23:50
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
public interface ArticleRepository extends BaseRepository<Article, String> {

    @Modifying
    @Query("update Article set status =:status,statusName =:statusName where id=:id")
    void changeStatus(@Param("id") String id, @Param("status") Integer status, @Param("statusName") String statusName);

}
