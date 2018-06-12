package com.miniits.base.dao;

import com.miniits.base.model.entity.ComponentImage;
import com.miniits.base.mysql.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/3/25
 * @Time: 16:31
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
public interface ComponentImageRepository extends BaseRepository<ComponentImage, String> {

    @Modifying
    @Query("update ComponentImage set componentStatus =:componentStatus,componentStatusName =:componentStatusName where id=:id")
    void changeStatus(@Param("id") String id, @Param("componentStatus") Integer componentStatus, @Param("componentStatusName") String componentStatusName);

    List<ComponentImage> findByComponentReferenceId(String componentReferenceId);

    ComponentImage findById(String id);

}
