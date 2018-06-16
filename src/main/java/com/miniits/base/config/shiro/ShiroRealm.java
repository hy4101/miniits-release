package com.miniits.base.config.shiro;

import com.miniits.base.model.entity.User;
import com.miniits.base.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/1/3
 * @Time: 23:12
 * <p>
 * Description:
 * ***
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //权限配置
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("ShiroRealm.doGetAuthenticationInfo()");
        String username = (String) authenticationToken.getPrincipal();
        System.out.println(authenticationToken.getCredentials());
        User user = userService.findByUserName(username);
        if (ObjectUtils.isEmpty(user)) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
        return authenticationInfo;
    }
}
