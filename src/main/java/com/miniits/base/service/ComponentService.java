package com.miniits.base.service;

import com.miniits.base.dao.ComponentRepository;
import com.miniits.base.model.entity.Component;
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
public class ComponentService extends BaseServiceImpl<Component, String> {

    @Autowired
    private ComponentRepository componentRepository;

    @Autowired
    public void setBaseDao(ComponentRepository componentRepository) {
        super.setBaseDao(componentRepository);
    }

}
