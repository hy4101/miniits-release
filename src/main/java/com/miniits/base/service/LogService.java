package com.miniits.base.service;

import com.miniits.base.dao.LogRepository;
import com.miniits.base.model.entity.Log;
import com.miniits.base.mysql.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: wq
 * @Date: 2018/2/2
 * @Time: 11:36
 * <p>
 * Description:
 * ***
 */
@Service
@Transactional
public class LogService extends BaseServiceImpl<Log, String> {

    @Autowired
    public void setBaseDao(LogRepository logRepository) {
        super.setBaseDao(logRepository);
    }

    @Autowired
    private LogRepository logRepository;

    public List<List<String>> counts() {
        return logRepository.counts();
    }


}
