package com.miniits.base.service;

import com.miniits.base.dao.ImageRepository;
import com.miniits.base.model.entity.Image;
import com.miniits.base.mysql.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/4/20
 * @Time: 22:42
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
@Service
@Transactional
public class ImageServer  extends BaseServiceImpl<Image, String> {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    public void setBaseDao(ImageRepository imageRepository) {
        super.setBaseDao(imageRepository);
    }

}
