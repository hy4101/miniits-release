package com.miniits.base.service;

import com.miniits.base.dao.PageRepository;
import com.miniits.base.model.entity.Page;
import com.miniits.base.mysql.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/3/24
 * @Time: 20:29
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */

@Service
@Transactional
public class PageService extends BaseServiceImpl<Page, String> {

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    public void setBaseDao(PageRepository pageRepository) {
        super.setBaseDao(pageRepository);
    }

}
