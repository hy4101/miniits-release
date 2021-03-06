package com.miniits.base.utils;

import com.miniits.base.mysql.Pageable;
import com.miniits.base.service.*;
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
        if (filter.indexOf("pageSize") >= 0) {
            return "";
        }
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

    /**
     * 为组建提供数据的 open API
     *
     * @param api
     * @param pageable
     * @return
     */
    public static Object getData(String api, Pageable pageable) {
        if (StringUtils.isEmpty(api)) {
            return null;
        }
        Map<String, Object> apis = new HashMap<>();
        switch (api) {
            case ApiNames.ARTICLE_SEARCH:
                apis.put("article/search", SpringContextHolder.getBean(ArticleServer.class).search(pageable));
                break;
            case ApiNames.ARTICLE_SEARCH_ONE:
                apis.put("article/search-one", SpringContextHolder.getBean(ArticleServer.class).search(pageable));
                break;
            case ApiNames.IMAGE_SEARCH:
                apis.put("image/search", SpringContextHolder.getBean(ImageServer.class).search(pageable));
                break;
            case ApiNames.IMAGE_SEARCH_ONE:
                apis.put("image/search-one", SpringContextHolder.getBean(ImageServer.class).search(pageable));
                break;
            case ApiNames.CATEGORY_SEARCH:
                apis.put("category/search", SpringContextHolder.getBean(CategoryServer.class).search(pageable));
                break;
            case ApiNames.CATEGORY_SEARCH_ONE:
                apis.put("category/search-one", SpringContextHolder.getBean(CategoryServer.class).search(pageable));
                break;
            case ApiNames.LINKS_SEARCH:
                apis.put("links/search", SpringContextHolder.getBean(LinksServer.class).search(pageable));
                break;
            case ApiNames.LINKS_SEARCH_ONE:
                apis.put("links/search-one", SpringContextHolder.getBean(LinksServer.class).search(pageable));
                break;
            case ApiNames.TAG_SEARCH:
                apis.put("tag/search", SpringContextHolder.getBean(TagServer.class).search(pageable));
                break;
            case ApiNames.TAG_SEARCH_ONE:
                apis.put("tag/search-one", SpringContextHolder.getBean(TagServer.class).search(pageable));
                break;
        }
        return apis.get(api);
    }

    public static String pageFilter(String filters) {
        return Arrays.stream(filters.split(";")).filter(filter -> filter.indexOf("pageSize") > 0 && StringUtils.isNotEmpty(filter)).collect(Collectors.joining(";"));
    }

    public static String dataFilter(String filters) {
        return Arrays.stream(filters.split(";")).map(DataUtil::apply).filter(StringUtils::isNotEmpty).collect(Collectors.joining(";"));
    }

    public static Map<String, Object> getPageData(String filters) {
        Map<String, Object> map = new HashMap<>();
        map.put("filters", dataFilter(filters));
        map.put("page", StringUtils.isNotEmpty(pageFilter(filters)) ? pageFilter(filters).split("=")[1] : "");
        return map;
    }
}
