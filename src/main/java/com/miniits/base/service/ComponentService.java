package com.miniits.base.service;

import com.miniits.base.dao.ComponentRepository;
import com.miniits.base.model.entity.Component;
import com.miniits.base.mysql.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.miniits.base.utils.SystemDict.ARTICLES_STATUS_ENABLE;

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
public class ComponentService extends BaseServiceImpl<Component, String> {

    @Autowired
    private ComponentRepository componentRepository;

    @Autowired
    public void setBaseDao(ComponentRepository componentRepository) {
        super.setBaseDao(componentRepository);
    }

    public void changeStatus(String id, Integer status) {
        componentRepository.changeStatus(id, status, status.equals(ARTICLES_STATUS_ENABLE) ? "启用" : "禁用");
    }
}
