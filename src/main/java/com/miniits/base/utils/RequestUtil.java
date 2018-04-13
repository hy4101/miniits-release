package com.miniits.base.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 扩展HttpServletRequest的功能，所有请求参数获取都通过该类方法来获取。
 *
 * @author www.miniits.com
 */
public class RequestUtil {
    public static final String APPFILEPROD = "https://fh.lmsy56.com/app/upgrade/";
    public static final String APPFILTEST = "http://192.168.1.113/app/upgrade/";
    public static final String DOWNLOAD = getRequest().getScheme() + "://" + getRequest().getServerName() + getRequest().getContextPath() + "/upload/";
    public static final String DOWNLOAD_UPLOAD = "upload/";
    public static final String PRESSRELEASEFILEDOWNLOAD = getRequest().getScheme() + "://" + getRequest().getServerName() + getRequest().getContextPath() + "/pressreleaseupload/";
    public static final String IDCARD = getRequest().getScheme() + "://" + getRequest().getServerName() + getRequest().getContextPath() + "/idcard/";
    public static final String DRIVER = getRequest().getScheme() + "://" + getRequest().getServerName() + getRequest().getContextPath() + "/driver/";
    public static final String ENTERPRISE = getRequest().getScheme() + "://" + getRequest().getServerName() + getRequest().getContextPath() + "/enterprise/";

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        if (requestAttributes != null) {
            return requestAttributes.getRequest();
        } else {
            return null;
        }
    }

    public static String getPath(String resourceName) {
        String path = null;
        try {
            path = getRequest().getClass().getClassLoader().getResource(resourceName).getPath();
        } catch (Exception e) {

        }
        return path;
    }

    /**
     * 获取所有request请求参数key-value
     *
     * @param request
     * @return
     */
    public static Map<String, String> getRequestParams(HttpServletRequest request) {

        Map<String, String> params = new HashMap<String, String>();
        if (null != request) {
            Set<String> paramsKey = request.getParameterMap().keySet();
            for (String key : paramsKey) {
                params.put(key, request.getParameter(key));
            }
        }
        return params;
    }

    /**
     * 获取所有request请求参数key-value
     *
     * @return
     */
    public static Map<String, String> getRequestParams() {

        Map<String, String> params = new HashMap<String, String>();
        if (null != getRequest()) {
            Set<String> paramsKey = getRequest().getParameterMap().keySet();
            for (String key : paramsKey) {
                params.put(key, getRequest().getParameter(key));
            }
        }
        return params;
    }

    /**
     * 获取请求参数中所有的信息
     *
     * @param request
     * @return
     */
    public static Map<String, String> getAllRequestParam(final HttpServletRequest request) {
        Map<String, String> res = new HashMap<String, String>();
        Enumeration<?> temp = request.getParameterNames();
        if (null != temp) {
            while (temp.hasMoreElements()) {
                String en = (String) temp.nextElement();
                String value = request.getParameter(en);
                res.put(en, value);
                //在报文上送时，如果字段的值为空，则不上送<下面的处理为在获取所有参数数据时，判断若值为空，则删除这个字段>
                //System.out.println("ServletUtil类247行  temp数据的键=="+en+"     值==="+value);
                if (null == res.get(en) || "".equals(res.get(en))) {
                    res.remove(en);
                }
            }
        }
        return res;
    }
}
