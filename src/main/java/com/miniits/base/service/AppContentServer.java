package com.miniits.base.service;

import com.miniits.base.dao.AppContentRepository;
import com.miniits.base.model.entity.AppContent;
import com.miniits.base.mysql.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: wq
 * @Date: 2018/5/16
 * @Time: 16:47
 * <p>
 * Description:
 * ***
 */
@Service
@Transactional
public class AppContentServer extends BaseServiceImpl<AppContent, String> {

    @Autowired
    private AppContentRepository appContentRepository;

    @Autowired
    public void setBaseDao(AppContentRepository appContentRepository) {
        super.setBaseDao(appContentRepository);
    }


}
