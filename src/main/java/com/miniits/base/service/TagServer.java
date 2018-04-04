package com.miniits.base.service;

import com.miniits.base.dao.TagRepositoy;
import com.miniits.base.model.entity.Tag;
import com.miniits.base.mysql.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: wq
 * @Date: 2018/4/3
 * @Time: 17:57
 * <p>
 * Description:
 * ***
 */

@Service
@Transactional
public class TagServer extends BaseServiceImpl<Tag, String> {

    @Autowired
    private TagRepositoy tagRepositoy;

    @Autowired
    public void setBaseDao(TagRepositoy tagRepositoy) {
        super.setBaseDao(tagRepositoy);
    }

    public Tag findByName(String name) {
        return tagRepositoy.findByName(name);
    }

    public void modifyTagNumber(List<String> name, Integer number) {
        tagRepositoy.modifyTagNumber(name, number);
    }
}
