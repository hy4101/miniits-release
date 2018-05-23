package com.miniits.base.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: wq
 * @Date: 2018/3/21
 * @Time: 17:06
 * <p>
 * Description:
 * ***
 */
@Controller
@RequestMapping("/admin")
public class AdminIndexController {

    @GetMapping
    public String index(ModelMap modelMap) {
        modelMap.put("active", "dashboard");
        return "admin/views/content/Articles";
    }

}
