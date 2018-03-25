package com.miniits.base.service;

import com.miniits.base.dao.ComponentImageReposiory;
import com.miniits.base.model.entity.ComponentImage;
import com.miniits.base.mysql.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/3/25
 * @Time: 16:31
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
@Service
@Transactional
public class ComponentImageServer extends BaseServiceImpl<ComponentImage, String> {

    @Autowired
    private ComponentImageReposiory componentImageReposiory;

    @Autowired
    public void setBaseDao(ComponentImageReposiory componentImageReposiory) {
        super.setBaseDao(componentImageReposiory);
    }

}
