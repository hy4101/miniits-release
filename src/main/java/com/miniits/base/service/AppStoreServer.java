package com.miniits.base.service;

import com.miniits.base.dao.AppStoreRepository;
import com.miniits.base.model.entity.AppStore;
import com.miniits.base.mysql.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

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
    private AppStoreRepository appStoreRepository;

    @Autowired
    public void setBaseDao(AppStoreRepository appStoreRepository) {
        super.setBaseDao(appStoreRepository);
    }

    public AppStore findBySystemNumberAndAscriptionNumber(String ascriptionNumber, String systemNumber) {
        List<AppStore> appStores = appStoreRepository.findBySystemNumberAndAscriptionNumber(systemNumber, ascriptionNumber);
        AppStore appStore = null;
        if (!ObjectUtils.isEmpty(appStores)) {
            appStore = appStores.get(0);
            appStore.setDownloadNumber(appStore.getDownloadNumber() + 1);
        }
        return appStore;
    }

}
