package com.miniits.base.dao;

import com.miniits.base.model.entity.User;
import com.miniits.base.mysql.BaseRepository;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/1/3
 * @Time: 21:35
 * <p>
 * Description:
 * ***
 */
public interface UserRepository extends BaseRepository<User, String> {

    User findByUserName(String username);

}
