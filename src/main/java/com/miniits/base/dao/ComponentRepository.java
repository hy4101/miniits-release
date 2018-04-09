package com.miniits.base.dao;

import com.miniits.base.model.entity.Component;
import com.miniits.base.mysql.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/3/24
 * @Time: 20:30
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
public interface ComponentRepository extends BaseRepository<Component, String> {

    @Modifying
    @Query("update Component set componentStatus =:componentStatus,componentStatusName =:componentStatusName where id=:id")
    void changeStatus(@Param("id") String id, @Param("componentStatus") Integer componentStatus, @Param("componentStatusName") String componentStatusName);

}
