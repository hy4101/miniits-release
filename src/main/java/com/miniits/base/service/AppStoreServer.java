package com.miniits.base.service;

import com.miniits.base.dao.DiyStoreRepository;
import com.miniits.base.model.entity.AppStore;
import com.miniits.base.mysql.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: wq
 * @Date: 2018/5/16
 * @Time: 16:21
 * <p>
 * Description:
 * ***
 */
@Service
@Transactional
public class AppStoreServer extends BaseServiceImpl<AppStore, String> {

    @Autowired
    private DiyStoreRepository diyStoreRepository;

    @Autowired
    public void setBaseDao(DiyStoreRepository diyStoreRepository) {
        super.setBaseDao(diyStoreRepository);
    }


}
