package com.miniits.base.config.shiro;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/6/14
 * @Time: 22:51
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
public class ShiroFilter extends FormAuthenticationFilter {

    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        //进行重写,业务逻辑
        return true;
    }
}
