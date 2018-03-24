package com.miniits.base.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/3/23
 * @Time: 23:56
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
@Controller
@RequestMapping("/admin/components")
public class ComponentsController {

    @GetMapping("init")
    public String init(ModelMap modelMap) {
        modelMap.put("active", "layout");
        return "admin/views/layout/Components";
    }

}
