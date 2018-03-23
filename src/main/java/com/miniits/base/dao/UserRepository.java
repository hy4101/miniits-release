package com.miniits.base.dao;

import com.miniits.base.model.entity.User;
import com.miniits.base.mysql.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    @Modifying
    @Query("update User set userStatusCode =:userStatusCode,userStatusName =:userStatusName where id=:id")
    void changeStatus(@Param("id") String id, @Param("userStatusCode") Integer userStatusCode, @Param("userStatusName") String userStatusName);

}
