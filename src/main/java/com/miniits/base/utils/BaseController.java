package com.miniits.base.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wq
 * @Date: 2018/1/6
 * @Time: 16:40
 * <p>
 * Description:
 * ***
 */
public class BaseController {

    @Autowired
    protected ObjectMapper objectMapper;

    public Result success(Object o) {
        Result result = new Result();
        if (null != o) {
            result.setObject(o);
        }
        result.setSuccess(true);
        return result;
    }

    public Result success(String message) {
        Result result = new Result();
        result.setSuccess(true);
        result.setMessage(message);
        return result;
    }

    public Result error(String errorMsg) {
        Result result = new Result();
        result.setSuccess(false);
        result.setMessage(errorMsg);
        return result;
    }

    public Result all(List list) {
        Result result = new Result();
        result.setSuccess(true);
        result.setRows(list);
        return result;
    }


    public Result page(List list) {
        Result result = new Result();
        result.setSuccess(true);
        result.setRows(list);
        return result;
    }

    /**
     * 将实体/模型对象转换成json数据
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public String toJson(Object obj) throws Exception {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    /**
     * 将json数据转换成实体/模型对象
     *
     * @param json
     * @param entityCls
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T toEntity(String json, Class<T> entityCls) throws Exception {
        try {
            T entity = objectMapper.readValue(json, entityCls);
            return entity;
        } catch (Exception ex) {
            throw new Exception("Unable to parse json, " + ex.getMessage());
        }
    }

    public <T> T toEntitys(String json, Class<T> entityCls) throws Exception {
        try {
            T entity = objectMapper.readValue(json, objectMapper.getTypeFactory().constructParametricType(ArrayList.class, entityCls));
            return entity;
        } catch (Exception ex) {
            throw new Exception("Unable to parse json, " + ex.getMessage());
        }
    }

    /**
     * 将对象转换成json，之后再转换成object
     *
     * @param ob
     * @param cls
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T toSkipEntity(Object ob, Class<T> cls) throws Exception {
        String json = toJson(ob);
        return toEntity(json, cls);
    }

}
