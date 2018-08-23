package com.miniits.base.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Transactional
public class BaseServiceImpls<T, R> implements BaseService<T, String> {

    @Autowired
    R repository;

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public T save(T entity) {
        return null;
    }

    @Override
    public List<T> save(List<T> list) {
        return null;
    }

    @Override
    public boolean exists(String s) {
        return false;
    }

    @Override
    public T findOne(String s) {
        return null;
    }

    @Override
    public T findOneByFilter(String filter) {
        return null;
    }

    @Override
    public T findOneByFilter(Map<String, Object> params) {
        return null;
    }

    @Override
    public T findOne(String field, Object value) {
        return null;
    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public List<T> findAll(String sorts) {
        return null;
    }

    @Override
    public List<T> findAll(List<String> strings) {
        return null;
    }

    @Override
    public List<T> findList(String field, Object value) {
        return null;
    }

    @Override
    public List<T> findList(String field, Object value, String sorts) {
        return null;
    }

    @Override
    public List<T> findList(String filters, String sorts) {
        return null;
    }

    @Override
    public List<T> findList(Map<String, Object> params, String sorts) {
        return null;
    }

    @Override
    public List<T> findList(String filters) {
        return null;
    }

    @Override
    public List<T> findList(Map<String, Object> params) {
        return null;
    }

    @Override
    public List<T> findDistinctList(String field, Object value) {
        return null;
    }

    @Override
    public List<T> findDistinctList(String field, Object value, String sorts) {
        return null;
    }

    @Override
    public List<T> findDistinctList(String filters, String sorts) {
        return null;
    }

    @Override
    public List<T> findDistinctList(Map<String, Object> params, String sorts) {
        return null;
    }

    @Override
    public List<T> findDistinctList(String filters) {
        return null;
    }

    @Override
    public List<T> findDistinctListOr(List<String> orFilters) {
        return null;
    }

    @Override
    public List<T> findDistinctList(Map<String, Object> params) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public long count(String filters) {
        return 0;
    }

    @Override
    public long count(Map<String, Object> params) {
        return 0;
    }

    @Override
    public void delete(String s) {

    }

    @Override
    public void delete(T entity) {

    }

    @Override
    public void delete(Iterable<T> entitys) {

    }

    @Override
    public void delete(List<String> strings) {

    }

    @Override
    public Page<T> search(String filters, Integer page, Integer size) {
        return null;
    }

    @Override
    public Page<T> searchOr(List<String> orFilters, Integer page, Integer size) {
        return null;
    }

    @Override
    public Page<T> search(Map<String, Object> params, Integer page, Integer size) {
        return null;
    }

    @Override
    public Page<T> search(Pageable pageable) {
        return null;
    }

    @Override
    public Page<T> searchOr(List<String> orFilters, String sorts, Integer page, Integer size) {
        return null;
    }

    @Override
    public Page<T> search(Map<String, Object> params, String sorts, Integer page, Integer size) {
        return null;
    }

    @Override
    public Page<T> searchDistinct(String filters, Integer page, Integer size) {
        return null;
    }

    @Override
    public Page<T> searchDistinct(Map<String, Object> params, Integer page, Integer size) {
        return null;
    }

    @Override
    public Page<T> searchDistinct(String filters, String sorts, Integer page, Integer size) {
        return null;
    }

    @Override
    public Page<T> searchDistinct(Map<String, Object> params, String sorts, Integer page, Integer size) {
        return null;
    }

    @Override
    public Page<T> searchDistinctOr(List<String> strings, String sorts, int page, int size) {
        return null;
    }
}
