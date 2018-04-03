package com.miniits.base.controller.admin;

import com.miniits.base.utils.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: wq
 * @Date: 2018/4/3
 * @Time: 17:57
 * <p>
 * Description:
 * ***
 */
@Controller
@RequestMapping("/admin/tags")
public class TagController extends BaseController {

    @GetMapping("init")
    public String init(ModelMap modelMap) {
        modelMap.put("active", "content");
        return "admin/views/content/tags";
    }
}
