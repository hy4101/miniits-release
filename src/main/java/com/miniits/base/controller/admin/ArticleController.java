package com.miniits.base.controller.admin;

import com.miniits.base.model.entity.Article;
import com.miniits.base.service.ArticleServer;
import com.miniits.base.utils.BaseController;
import com.miniits.base.utils.ConvertUtil;
import com.miniits.base.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/3/28
 * @Time: 23:51
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
@Controller
@RequestMapping("/admin/contents/article")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleServer articleServer;

    @GetMapping("init")
    public String init(ModelMap modelMap) {
        modelMap.put("active", "content");
        return "admin/views/content/Articles";
    }

    @GetMapping("publish/init")
    public String publish(ModelMap modelMap) {
        modelMap.put("active", "content");
        return "admin/views/content/ArticlesPublish";
    }

    @PostMapping("/publish")
    @ResponseBody
    public Result saveArticle(@RequestParam(value = "article") String article) {
        return success(articleServer.save(ConvertUtil.toVO(article, Article.class)));
    }

}
