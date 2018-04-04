package com.miniits.base.controller.admin;

import com.miniits.base.model.entity.Article;
import com.miniits.base.service.ArticleServer;
import com.miniits.base.service.CategoryServer;
import com.miniits.base.service.TagServer;
import com.miniits.base.utils.BaseController;
import com.miniits.base.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static com.miniits.base.utils.SystemDict.ARTICLES_STATUS_ENABLE;
import static com.miniits.base.utils.SystemDict.ARTICLES_TYPE_USER;

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

    @Autowired
    private TagServer tagServer;

    @Autowired
    private CategoryServer categoryServer;

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
    public Result saveArticle(@RequestParam(value = "article") String article) throws Exception {
        Article o = toEntity(article, Article.class);
        List<String> tags = Arrays.asList(o.getTags().split(","));
        List<String> types = Arrays.asList(o.getTypeNames().split(","));
        tagServer.modifyTagNumber(tags, 1);
        o.setSource(ARTICLES_TYPE_USER);
        o.setSourceName("系统");
        o.setStatus(ARTICLES_STATUS_ENABLE);
        o.setStatusName("启用");
        o.setAuthorId("");
        o.setAuthorName("");
        return success(articleServer.save(o));
    }

}
