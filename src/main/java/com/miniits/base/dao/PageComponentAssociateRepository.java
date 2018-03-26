package com.miniits.base.dao;

import com.miniits.base.model.entity.PageComponentAssociate;
import com.miniits.base.mysql.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/3/24
 * @Time: 20:30
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
public interface PageComponentAssociateRepository extends BaseRepository<PageComponentAssociate, String> {

    void deleteByPage_Id(String pageId);

    List<PageComponentAssociate> findByPage_Id(String pageId);

    PageComponentAssociate findFirstByComponentImage_IdOrderBySortsDesc(String pId);

    PageComponentAssociate findFirstByComponentImagePId_IdOrderBySortsDesc(String pId);

    PageComponentAssociate findByPage_IdAndLevelAndSorts(String pageId, Integer level, Integer sorts);

    PageComponentAssociate findByComponentImagePId_IdAndLevelAndSorts(String componentImagePId, Integer level, Integer sorts);

    @Modifying
    @Query("update PageComponentAssociate set sorts =:sorts where id =:id")
    void revisionSort(@Param("id") String id, @Param("sorts") Integer sorts);

}
