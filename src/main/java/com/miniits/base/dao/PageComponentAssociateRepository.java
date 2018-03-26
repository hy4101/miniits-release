package com.miniits.base.dao;

import com.miniits.base.model.entity.PageComponentAssociate;
import com.miniits.base.mysql.BaseRepository;

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

}
