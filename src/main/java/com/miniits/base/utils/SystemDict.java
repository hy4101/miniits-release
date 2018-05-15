package com.miniits.base.utils;

/**
 * @author: wq
 * @Date: 2018/1/6
 * @Time: 16:19
 * <p>
 * Description:
 * ***
 */
public final class SystemDict {


    /**
     * 全局状态
     * 是 = 100000001
     * 否 = 100000002
     */
    public final static Integer GLOBAL_STATUS_YES = 100000001;
    public final static Integer GLOBAL_STATUS_NO = 100000002;

    /**
     * 文章类型
     * 用户 = 100001001
     * 系统 = 100001002
     * 第三方 = 100001003
     */
    public final static Integer ARTICLES_TYPE_USER = 100001001;
    public final static Integer ARTICLES_TYPE_SYSTEM = 100001002;
    public final static Integer ARTICLES_TYPE_THIRD_PARTY = 100001003;

    /**
     * 文章状态
     * 显示 = 100001001
     * 隐藏 = 100001002
     */
    public final static Integer ARTICLES_STATUS_ENABLE = 100002001;
    public final static Integer ARTICLES_STATUS_DISABLED = 100002002;

    /**
     * 站点状态
     * 待验证 = 100003001
     * 可用 = 100003002
     * 禁用 = 100003003
     */
    public final static Integer WEBSITE_STATUS_NOT_EMAIL_VERIFICATION = 100003001;
    public final static Integer WEBSITE_STATUS_YET_VERIFICATION = 100003002;
    public final static Integer WEBSITE_STATUS_NOT_USE = 100003003;

    /**
     * 站点/书签来源
     * INFINITY = 100004001
     * 收藏 = 100004002
     * 用户分享/用户添加 = 100004003
     */
    public final static Integer WEBSITE_SOURCE_INFINITY = 100004001;
    public final static Integer WEBSITE_SOURCE_ENSHRINE = 100004002;
    public final static Integer WEBSITE_SOURCE_USER_SHARE = 100004003;

    /**
     * 用户类型
     * Btbtt88 = 100005001
     * xunleipu = 100005002
     * kantiantang = 100005003
     * btbtdy = 100005004
     * xl720 = 100005005
     * ygdy8 = 100005006
     * haotor = 100005007
     * bt1280 = 100005008
     */
    public final static Integer MOVIE_SOURCE_BTBTT88 = 100005001;
    public final static Integer MOVIE_SOURCE_XUNLEIPU = 100005002;
    public final static Integer MOVIE_SOURCE_KANTIANTANG = 100005003;
    public final static Integer MOVIE_SOURCE_BTBTDY = 100005004;
    public final static Integer MOVIE_SOURCE_XL720 = 100005005;
    public final static Integer MOVIE_SOURCE_YGDY8 = 100005006;
    public final static Integer MOVIE_SOURCE_HAOTOR = 100005007;
    public final static Integer MOVIE_SOURCE_BT1280 = 100005008;

    /**
     * 组件
     * 100
     */
    public static class Component {

        /**
         * 来源
         * 系统 = 100006001
         * 个人 = 100006002
         */
        public final static Integer COMPONENT_SOURCE_SYSTEM = 100006001;
        public final static Integer COMPONENT_SOURCE_DIY = 100006002;
        /**
         * 类型
         * 分页 = 101006001
         */
        public final static Integer COMPONENT_TYPE_PAGE = 101006001;
    }

    /**
     * API数据结构类型
     * 数组对象-分页 = 100007001
     * 对象 = 100007002
     * 数组对象-不分页 = 100007003
     */
    public final static Integer API_DATA_STRUCTURE_TYPE_PAGE = 100007001;
    public final static Integer API_DATA_STRUCTURE_TYPE = 100007002;
    public final static Integer API_DATA_STRUCTURE_TYPE_NO_PAGE = 100007003;

}
