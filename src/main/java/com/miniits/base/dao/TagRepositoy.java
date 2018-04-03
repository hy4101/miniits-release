package com.miniits.base.dao;

import com.miniits.base.model.entity.Tag;
import com.miniits.base.mysql.BaseRepository;

/**
 * @author: wq
 * @Date: 2018/4/3
 * @Time: 17:56
 * <p>
 * Description:
 * ***
 */
public interface TagRepositoy extends BaseRepository<Tag, String> {

    Tag findByName(String name);

}
