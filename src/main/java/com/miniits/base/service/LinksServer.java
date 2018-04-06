package com.miniits.base.service;

import com.miniits.base.dao.LinksRepository;
import com.miniits.base.model.entity.Links;
import com.miniits.base.mysql.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.miniits.base.utils.SystemDict.GLOBAL_STATUS_YES;


/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/4/6
 * @Time: 17:41
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */

@Service
@Transactional
public class LinksServer extends BaseServiceImpl<Links, String> {

    @Autowired
    private LinksRepository linksRepository;

    @Autowired
    public void setBaseDao(LinksRepository linksRepository) {
        super.setBaseDao(linksRepository);
    }

    public void changeStatus(String id, Integer status) {
        linksRepository.changeStatus(id, status, status.equals(GLOBAL_STATUS_YES) ? "启用" : "禁用");
    }
}
