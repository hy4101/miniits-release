package com.miniits.base.dao;


import com.miniits.base.model.entity.Log;
import com.miniits.base.mysql.BaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author: wq
 * @Date: 2018/2/2
 * @Time: 11:36
 * <p>
 * Description:
 * ***
 */
public interface LogRepository extends BaseRepository<Log, String> {

    @Query(value = "select region ,count(*) as col from logs where region <>'XX' group by region;", nativeQuery = true)
    List<List<String>> counts();

}
