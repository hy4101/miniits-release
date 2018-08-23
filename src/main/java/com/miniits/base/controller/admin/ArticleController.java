package com.miniits.base.controller.admin;

import com.miniits.base.model.entity.Article;
import com.miniits.base.mysql.Pageable;
import com.miniits.base.service.ArticleServer;
import com.miniits.base.service.CategoryServer;
import com.miniits.base.service.TagServer;
import com.miniits.base.utils.BaseController;
import com.miniits.base.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    public String publish(ModelMap modelMap, @RequestParam(value = "id") String id) {
        modelMap.put("active", "content");
        if (!StringUtils.isEmpty(id)) {
            modelMap.put("article", articleServer.findOne(id));
        }
        return "admin/views/content/ArticlesPublish";
    }

    @DeleteMapping("{id}")
    @ResponseBody
    public Result pages(@PathVariable(value = "id") String id) {
        articleServer.delete(id);
        return success("删除成功");
    }

    @GetMapping
    @ResponseBody
    public Result pages(Pageable pageable) {
        Page<Article> articles = articleServer.search(pageable);
        return page(articles.getContent()).page(pageable.getPageSize()).size(pageable.getPageNumber()).
                totalCount(articles.getTotalElements()).total(articles.getTotalElements());
    }

    @PostMapping("/change/status")
    @ResponseBody
    public Result changeStatus(@RequestParam(value = "id") String id, @RequestParam(value = "status") Integer status) {
        articleServer.changeStatus(id, status);
        return success("更改成功");
    }

    @PostMapping("/publish")
    @ResponseBody
    public Result saveArticle(@RequestParam(value = "article") String article) throws Exception {
        Article o = toEntity(article, Article.class);
        List<String> tags = Arrays.asList(o.getTags().split(","));
        List<String> types = Arrays.asList(o.getTypeNames().split(","));

        /**
         * new
         */
        if (StringUtils.isEmpty(o.getId())) {
            o = initDefaultValue(o);
            o.setSource(ARTICLES_TYPE_USER);
            o.setSourceName("系统");
            o.setStatus(ARTICLES_STATUS_ENABLE);
            o.setStatusName("启用");
            o.setAuthorId("");
            o.setAuthorName("");
            o = articleServer.save(o);

            tagServer.modifyTagNumber(tags, 1);
            categoryServer.modifyCategoryNumber(types, 1);

        } else {
            /**
             * update
             */
            Article old = articleServer.findOne(o.getId());
            List<String> oldTags = Arrays.asList(old.getTags().split(","));
            List<String> oldTypes = Arrays.asList(old.getTypeNames().split(","));

            old.setTitleName(o.getTitleName());
            old.setTitleImage(o.getTitleImage());
            old.setContentTitle(o.getContentTitle());
            old.setContent(o.getContent());
            old.setTypeNames(o.getTypeNames());
            old.setTags(o.getTags());
            old.setKeys(o.getKeys().replaceAll("，", ","));
            old.setAllowComment(o.getAllowComment());

            List<String> removeTags = oldTags.stream().filter(tag -> !StringUtils.isEmpty(tag) && !tags.contains(tag)).collect(Collectors.toList());
            List<String> addTags = tags.stream().filter(tag -> !StringUtils.isEmpty(tag) && !oldTags.contains(tag)).collect(Collectors.toList());

            List<String> removeTypes = oldTypes.stream().filter(type -> !StringUtils.isEmpty(type) && !types.contains(type)).collect(Collectors.toList());
            List<String> addTypes = types.stream().filter(type -> !StringUtils.isEmpty(type) && !oldTypes.contains(type)).collect(Collectors.toList());

            o = old;
            o = articleServer.save(o);

            if (!ObjectUtils.isEmpty(removeTags)) {
                tagServer.modifyTagNumber(removeTags, -1);
            }
            if (!ObjectUtils.isEmpty(addTags)) {
                tagServer.modifyTagNumber(addTags, 1);
            }

            if (!ObjectUtils.isEmpty(removeTypes)) {
                categoryServer.modifyCategoryNumber(removeTypes, -1);
            }
            if (!ObjectUtils.isEmpty(addTypes)) {
                categoryServer.modifyCategoryNumber(addTypes, 1);
            }
        }

        return success(o);
    }

    private Article initDefaultValue(Article o) {
        if (StringUtils.isEmpty(o.getId())) {
            o.setHits(0);
        }
        return o;
    }

}
