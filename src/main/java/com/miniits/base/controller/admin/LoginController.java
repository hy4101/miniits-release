package com.miniits.base.controller.admin;

import com.miniits.base.model.entity.User;
import com.miniits.base.service.UserService;
import com.miniits.base.utils.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.miniits.base.utils.MD5Util.hashStr;

/**
 * @author: wq
 * @Date: 2018/6/12
 * @Time: 17:29
 * <p>
 * Description:
 * ***
 */
@Controller
@RequestMapping("/admin/login")
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public String init() {
        return "admin/views/Login";
    }

    @PostMapping()
    public String login(ModelMap modelMap, @RequestParam(value = "userName") String userName, @RequestParam(value = "password") String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, hashStr(password));
        User user = null;
        try {
            subject.login(token);
            user = userService.findByUserName(userName);
        } catch (AuthenticationException e) {
        }
        modelMap.put("user", user);
        modelMap.put("active", "layout");
        return "admin/views/layout/Pages";
    }

    @PostMapping("logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "redirect:/admin/login";
    }

}
