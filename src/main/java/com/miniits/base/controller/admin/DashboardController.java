package com.miniits.base.controller.admin;

import com.miniits.base.utils.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/6/17
 * @Time: 13:49
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
@Controller
@RequestMapping("/admin/dashboard")
public class DashboardController extends BaseController {

    @GetMapping("init")
    public String init(ModelMap modelMap) {
        modelMap.put("active", "dashboard");
        return "admin/views/system/Dashboard";
    }

}
