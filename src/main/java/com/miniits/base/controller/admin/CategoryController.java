package com.miniits.base.controller.admin;

import com.miniits.base.model.entity.Category;
import com.miniits.base.model.entity.Tag;
import com.miniits.base.model.vo.CategoryAndTag;
import com.miniits.base.mysql.Pageable;
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

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/4/1
 * @Time: 21:48
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
@Controller
@RequestMapping("/admin/categorys")
public class CategoryController extends BaseController {

    @Autowired
    private CategoryServer categoryServer;

    @Autowired
    private TagServer tagServer;

    @GetMapping("init")
    public String init(ModelMap modelMap) {
        modelMap.put("active", "content");
        return "admin/views/content/Categorys";
    }

    @GetMapping
    @ResponseBody
    public Result pages(Pageable pageable) {
        Page<Category> categories = categoryServer.search(pageable);
        return page(categories.getContent()).page(pageable.getPageSize()).size(pageable.getPageNumber()).
                totalCount(categories.getTotalElements()).total(categories.getTotalElements());
    }

    @GetMapping(value = "/category-and-tag")
    @ResponseBody
    public Result searchCTS(Pageable pageable) {
        Page<Category> categories = categoryServer.search(pageable);
        Page<Tag> tags = tagServer.search(pageable);
        return success(new CategoryAndTag(categories.getContent(), tags.getContent()));
    }

    @PostMapping
    @ResponseBody
    public Result saveCategory(@RequestParam(value = "category") String category) throws Exception {
        Category o = toEntity(category, Category.class);
        String mes = "添加成功";
        if (StringUtils.isEmpty(o.getId())) {
            o.setNumber(0);
            long number = categoryServer.countExtend("EQ_categoryName=" + o.getCategoryName());
            if (number > 0) {
                return error("【 " + o.getCategoryName() + " 】类别已经存在");
            }
            categoryServer.save(o);
        } else {
            Category old = categoryServer.findByCategoryName(o.getCategoryName());
            if (!ObjectUtils.isEmpty(old) && !old.getId().equals(o.getId()) && old.getCategoryName().equals(o.getCategoryName())) {
                return error("【 " + o.getCategoryName() + " 】类别已经存在");
            }
            categoryServer.save(o);
            mes = "修改成功";
        }
        return success(mes);
    }

    @DeleteMapping("{id}")
    @ResponseBody
    public Result delete(@PathVariable(value = "id") String id) {
        if (!id.isEmpty()) {
            categoryServer.delete(id);
        }
        return success("删除成功");
    }


}
