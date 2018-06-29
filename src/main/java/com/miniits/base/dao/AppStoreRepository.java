package com.miniits.base.dao;


import com.miniits.base.model.entity.AppStore;
import com.miniits.base.mysql.BaseRepository;

import java.util.List;

/**
 * @author: wq
 * @Date: 2018/5/16
 * @Time: 16:20
 * <p>
 * Description:
 * ***
 */
public interface AppStoreRepository extends BaseRepository<AppStore, String> {

    List<AppStore> findBySystemNumberAndAscriptionNumber(String systemNumber, String ascriptionNumber);

}
