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
     * 用户类型
     * 迷你书签 = 100001001
     * 迷你科技 = 100001002
     * 平台 = 100001003
     */
    public final static Integer USER_TYPE_MINI_BK = 100001001;
    public final static Integer USER_TYPE_MINI_ITS = 100001002;
    public final static Integer USER_TYPE_PLATFORM = 100001003;

    /**
     * 用户状态
     * 待验证 = 100001001
     * 验证通过 = 100001002
     * 禁用 = 100001003
     */
    public final static Integer USER_STATUS_NOT_EMAIL_VERIFICATION = 100002001;
    public final static Integer USER_STATUS_YET_VERIFICATION = 100002002;
    public final static Integer USER_STATUS_NOT_USE = 100002003;

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

}
