package com.miniits.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/3/16
 * @Time: 22:13
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
@Controller
@RequestMapping("/fc")
public class IndexController {

    @GetMapping
    public String test(ModelMap modelMap) {
        modelMap.put("title", "the is title");
        modelMap.put("content", "HotArticles");
        modelMap.put("description", "the is description");
        modelMap.put("keywords", "the is keywords");
        return "default/Index";
    }

    @GetMapping("/{path}")
    public String path(
            @PathVariable(value = "path") String path,
            ModelMap modelMap) {
        return "default/Article-Detail";
    }

    @GetMapping("test")
    public String tes(ModelMap modelMap) {
        modelMap.put("title", "the is title");
        modelMap.put("content", "HotArticles");
        modelMap.put("description", "the is description");
        modelMap.put("keywords", "the is keywords");
        return "/fd";
    }

}
