package com.miniits.base.controller.admin;

import com.miniits.base.utils.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("init")
    public String init() {
        return "admin/views/Login";
    }

    @GetMapping()
    public String login(ModelMap modelMap) {
        modelMap.put("active", "layout");
        return "admin/views/layout/Pages";
    }
}
