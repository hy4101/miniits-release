package com.miniits.base.service;

import com.miniits.base.dao.PageComponentAssociateRepository;
import com.miniits.base.model.entity.PageComponentAssociate;
import com.miniits.base.mysql.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/3/24
 * @Time: 20:31
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
public class PageComponentAssociateService extends BaseServiceImpl<PageComponentAssociate, String> {

    @Autowired
    private PageComponentAssociateRepository pageComponentAssociateRepository;

    @Autowired
    public void setBaseDao(PageComponentAssociateRepository pageComponentAssociateRepository) {
        super.setBaseDao(pageComponentAssociateRepository);
    }

}
