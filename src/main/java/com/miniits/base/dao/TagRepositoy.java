package com.miniits.base.dao;

import com.miniits.base.model.entity.Tag;
import com.miniits.base.mysql.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

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

    @Modifying
    @Query("update Tag set number = number + (:number) where id in (:name)")
    void modifyTagNumber(@Param("name") List<String> name, @Param("number") Integer number);

}
