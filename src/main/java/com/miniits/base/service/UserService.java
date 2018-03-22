package com.miniits.base.service;

import com.miniits.base.dao.UserRepository;
import com.miniits.base.model.entity.User;
import com.miniits.base.mysql.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/1/3
 * @Time: 21:35
 * <p>
 * Description:
 * ***
 */

@Service
@Transactional
public class UserService extends BaseServiceImpl<User, String> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public void setBaseDao(UserRepository userRepository) {
        super.setBaseDao(userRepository);
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
