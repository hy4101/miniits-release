package com.miniits.base.controller.admin;

import com.miniits.base.utils.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/4/20
 * @Time: 22:43
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
@Controller
@RequestMapping("/admin/images")
public class ImageController extends BaseController {

    @GetMapping("init")
    public String init(ModelMap modelMap) {
        modelMap.put("active", "content");
        return "admin/views/content/Images";
    }


}
