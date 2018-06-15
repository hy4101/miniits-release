package com.miniits.base.config.shiro;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by wq on 2018/6/15.
 */
public class CustomAuthenticationFilter extends FormAuthenticationFilter {

    private Set<String> ignorePath = new HashSet<>();

    public void setIgnorePath(Set<String> ignorePath) {
        this.ignorePath = ignorePath;
    }

    public void addIgnorePath(String path) {
        this.ignorePath.add(path);
    }

//    @Override
//    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
//        String path=getPathWithinApplication(request);
//        if(ignorePath.contains(path)) {
//            return true;
//        }else{
//            return true;
////            return super.isAccessAllowed(request, response, mappedValue);
//        }
//    }

//    @Override
//    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//        String path = getPathWithinApplication(request);
//        User user = (User) SecurityUtils.getSubject().getPrincipal();
//        return true;
//        if (ignorePath.contains(path)) {
//            return true;
//        } else {
//            return false;
//        }
//    }
}
