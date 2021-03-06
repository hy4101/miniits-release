package com.miniits.base.dao;

import com.miniits.base.model.entity.Page;
import com.miniits.base.mysql.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/3/24
 * @Time: 20:28
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
public interface PageRepository extends BaseRepository<Page, String> {

    @Modifying
    @Query("update Page set pageStatus =:pageStatus,pageStatusName =:userStatusName where id=:id")
    void changeStatus(@Param("id") String id, @Param("pageStatus") Integer pageStatus, @Param("userStatusName") String userStatusName);

    @Modifying
    @Query("update Page set createStaticFile =:createStaticFile where id =:id")
    void setCreateHtmlFile(@Param("id") String id, @Param("createStaticFile") Integer createStaticFile);

    @Modifying
    @Query("update Page set keywords =:keywords,title =:title,description =:description where id =:id")
    void setPageSeo(@Param("id") String id, @Param("keywords") String keywords, @Param("title") String title, @Param("description") String description);

    Page findByPageNameAndPageStatus(@Param("pageName") String pageName, @Param("pageStatus") Integer pageStatus);

}
