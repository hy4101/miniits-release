package com.miniits.base.utils;

import com.miniits.base.mysql.Pageable;
import com.miniits.base.service.ArticleServer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wq
 * @Date: 2018/4/11
 * @Time: 12:28
 * <p>
 * Description:
 * ***
 */
public class ApiUtil {

    public static Object getData(String api, Pageable pageable) {
        Map<String, Object> apis = new HashMap<>();
        apis.put("search/article", SpringContextHolder.getBean(ArticleServer.class).search(pageable));
        return apis.get(api);
    }
}
