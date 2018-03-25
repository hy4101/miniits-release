package com.miniits.base.service;

import com.miniits.base.dao.PageComponentAssociateRepository;
import com.miniits.base.dao.PageRepository;
import com.miniits.base.model.entity.Page;
import com.miniits.base.mysql.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import static com.miniits.base.utils.SystemDict.GLOBAL_STATUS_YES;

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
    private PageComponentAssociateRepository pageComponentAssociateRepository;

    @Autowired
    public void setBaseDao(PageRepository pageRepository) {
        super.setBaseDao(pageRepository);
    }

    public void changeStatus(String id, Integer userStatusCode) {
        pageRepository.changeStatus(id, userStatusCode, userStatusCode.equals(GLOBAL_STATUS_YES) ? "启用" : "禁用");
    }

    public void delete(String id) {
        if (!ObjectUtils.isEmpty(pageComponentAssociateRepository.findByPage_Id(id))) {
            pageComponentAssociateRepository.deleteByPage_Id(id);
        }
        pageRepository.delete(id);
    }

}
