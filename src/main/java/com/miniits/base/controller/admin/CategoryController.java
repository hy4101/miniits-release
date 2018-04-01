package com.miniits.base.controller.admin;

import com.miniits.base.model.entity.Category;
import com.miniits.base.mysql.Pageable;
import com.miniits.base.service.CategoryServer;
import com.miniits.base.utils.BaseController;
import com.miniits.base.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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

    @PostMapping
    @ResponseBody
    public Result saveCategory(@RequestParam(value = "id") String id, @RequestParam(value = "categoryName") String categoryName) {
        if (id.isEmpty()) {
            long number = categoryServer.countExtend("EQ_categoryName=" + categoryName);
            if (number > 0) {
                return error("【 " + categoryName + " 】类别已经存在");
            }
            categoryServer.save(new Category(categoryName));
        } else {
            Category category = categoryServer.findOne(id);
            category.setCategoryName(categoryName);
            category = categoryServer.save(category);
        }
        return success("添加成功");
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
