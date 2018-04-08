package com.miniits.base.controller;

import com.miniits.base.model.entity.Page;
import com.miniits.base.service.PageService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private PageService pageService;

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

    @GetMapping("index")
    public String index() {
        StringBuffer html = new StringBuffer();
        html.append("<div ad=1>1123</div><div ad=1><span>sp</span></div>");
        Page page = pageService.getPage("index", 100000001);
        String content = "blabla";
        Document doc = Jsoup.parse(html.toString());
        Elements element = doc.getElementsByAttributeValue("ad", "1");
        return "fd";
    }
}
