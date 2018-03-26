package com.miniits.base.service;

import com.miniits.base.dao.PageComponentAssociateRepository;
import com.miniits.base.model.entity.PageComponentAssociate;
import com.miniits.base.mysql.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/3/24
 * @Time: 20:31
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */

@Service
@Transactional
public class PageComponentAssociateService extends BaseServiceImpl<PageComponentAssociate, String> {

    @Autowired
    private PageComponentAssociateRepository pageComponentAssociateRepository;

    @Autowired
    public void setBaseDao(PageComponentAssociateRepository pageComponentAssociateRepository) {
        super.setBaseDao(pageComponentAssociateRepository);
    }

    public PageComponentAssociate findFirstBycomponentAndComponentImagePId_Id(String pId) {
        return pageComponentAssociateRepository.findFirstByComponentImage_IdOrderBySortsDesc(pId);
    }

    public PageComponentAssociate findFirstByComponentImagePId_IdOrderBySortsDesc(String pId) {
        return pageComponentAssociateRepository.findFirstByComponentImagePId_IdOrderBySortsDesc(pId);
    }
}
