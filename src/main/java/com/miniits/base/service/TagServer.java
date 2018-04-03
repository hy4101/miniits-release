package com.miniits.base.service;

import com.miniits.base.model.entity.Tag;
import com.miniits.base.mysql.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
