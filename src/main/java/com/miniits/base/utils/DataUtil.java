package com.miniits.base.utils;

import com.miniits.base.mysql.Pageable;
import com.miniits.base.service.ArticleServer;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: wq
 * @Date: 2018/4/11
 * @Time: 12:28
 * <p>
 * Description:
 * ***
 */
public class DataUtil {

    private static String apply(String filter) {
        if (filter.indexOf("=") > 0) {
            return "EQ_" + filter;
        }
        if (filter.indexOf("?") > 0) {
            filter.replaceAll("\\?", "=");
            return "LIKE_" + filter;
        }
        if (filter.indexOf(">") > 0) {
            filter.replaceAll(">", "=");
            return "GT_" + filter;
        }
        if (filter.indexOf("<") > 0) {
            filter.replaceAll("<", "=");
            return "LT_" + filter;
        }
        if (filter.indexOf(">=") > 0) {
            filter.replaceAll(">=", "=");
            return "GTE_" + filter;
        }
        if (filter.indexOf("<=") > 0) {
            filter.replaceAll("<=", "=");
            return "LTE_" + filter;
        }
        return null;
    }

    public enum Joiner {
        // 不等于
        NOTEQ,
        // 等于
        EQ,
        // like
        LIKE,
        // 左like
        LLIKE,
        // 右like
        RLIKE,
        // is null
        ISNULL,
        // is not null
        ISNOTNULL,
        // 为空
        ISEMPTY,
        // 不为空
        ISNOTEMPTY,
        // in
        IN,
        // not in
        NOTIN,
        // 大于
        GT,
        // 小于
        LT,
        // 大于等于
        GTE,
        // 小于等于
        LTE,
        // between
        BETWEEN,
        // like in
        LIKEIN,
        // 待逗号的 like
        LIKEINCOMMA,
        // 日期 大于当前时间几分钟
        GTMINUTE,
        // 日期 小于当前时间几分钟
        LTMINUTE,
        // 是否 null 值 true 为 is not null，false 为 is null
        SPECIFIED
    }

    public static Object getData(String api, Pageable pageable) {
        Map<String, Object> apis = new HashMap<>();
        apis.put("article/search", SpringContextHolder.getBean(ArticleServer.class).search(pageable));
        apis.put("article/search-one", SpringContextHolder.getBean(ArticleServer.class).search(pageable));
        return apis.get(api);
    }

    public static String filters(String filters) {
        filters = Arrays.stream(filters.split(";")).map(DataUtil::apply).filter(StringUtils::isNotEmpty).collect(Collectors.joining(";"));
        return filters;
    }

}
