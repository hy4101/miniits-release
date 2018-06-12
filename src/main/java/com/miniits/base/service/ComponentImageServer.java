package com.miniits.base.service;

import com.miniits.base.dao.ComponentImageRepository;
import com.miniits.base.model.entity.ComponentImage;
import com.miniits.base.mysql.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.miniits.base.utils.SystemDict.GLOBAL_STATUS_YES;

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
    private ComponentImageRepository componentImageReposiory;

    @Autowired
    public void setBaseDao(ComponentImageRepository componentImageReposiory) {
        super.setBaseDao(componentImageReposiory);
    }

    public void changeStatus(String id, Integer userStatusCode) {
        componentImageReposiory.changeStatus(id, userStatusCode, userStatusCode.equals(GLOBAL_STATUS_YES) ? "启用" : "禁用");
    }

    public List<ComponentImage> findByComponentReferenceId(String componentReferenceId) {
        return componentImageReposiory.findByComponentReferenceId(componentReferenceId);
    }

    public ComponentImage getObjectKey(String id, String objectKey) {
        ComponentImage componentImage = componentImageReposiory.findById(id);
        if (StringUtils.isEmpty(componentImage.getObjectKey())) {
            componentImage.setObjectKey(objectKey);
        }
        return componentImage;
    }
}
