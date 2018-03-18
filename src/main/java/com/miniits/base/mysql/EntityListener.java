package com.miniits.base.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.PrePersist;


/**
 * Entity保存时自动修改创建时间跟修改时间
 *
 * @author wq
 */
public class EntityListener {

    //日志
    protected static Logger logger = LoggerFactory.getLogger(EntityListener.class);

    /**
     * 保存前处理
     *
     * @param entity 基类
     */
    @PrePersist
    public void prePersist(BaseEntity entity) {
//        User currentUser = (User) SecurityUtils.getSubject().getPrincipal();
//        entity.setCreateDate(Calendar.getInstance().getTime());
//        entity.setCreateBy(null != currentUser ? currentUser.getId() : null);
    }
}