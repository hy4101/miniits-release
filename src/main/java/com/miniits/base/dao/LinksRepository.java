package com.miniits.base.dao;

import com.miniits.base.model.entity.Links;
import com.miniits.base.mysql.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/4/6
 * @Time: 17:40
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
public interface LinksRepository extends BaseRepository<Links, String> {

    @Modifying
    @Query("update Links set status =:status,statusName =:statusName where id=:id")
    void changeStatus(@Param("id") String id, @Param("status") Integer status, @Param("statusName") String statusName);

}
