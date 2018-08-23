//package com.miniits.base.mysql;
//
//import com.yihu.ehr.constants.PageArg;
//import org.apache.commons.lang.StringUtils;
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.PagingAndSortingRepository;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//import javax.persistence.metamodel.EntityType;
//import java.io.Serializable;
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.Type;
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * Service基础类。此类基于Spring Data JPA进行封装（Spring Data JPA又是基于JPA封装，EHR平台使用Hibernate作为JPA实现者）。
// * 需要注意的是，部分功能会跳过JPA接口而直接使用Hibernate接口，比如访问Hibernate的Session接口，因为它把JPA的EntityManager功能强大。
// *
// * @author lincl
// * @author Sand
// * @version 1.0
// * @created 2016.2.3
// */
//@Transactional(propagation = Propagation.SUPPORTS)
//public class BaseJpaService<T, R> {
//    @Autowired
//    R repo;
//
//    @PersistenceContext
//    protected EntityManager entityManager;
//
//    public void df(){
//        repo
//    }
//
//    public PagingAndSortingRepository getRepository() {
//        return (PagingAndSortingRepository) repo;
//    }
//
//    public JpaRepository getJpaRepository(){
//        return (JpaRepository)repo;
//    }
//
//}
