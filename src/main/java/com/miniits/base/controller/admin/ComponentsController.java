package com.miniits.base.controller.admin;

import com.miniits.base.model.entity.Component;
import com.miniits.base.mysql.Pageable;
import com.miniits.base.service.ComponentService;
import com.miniits.base.utils.BaseController;
import com.miniits.base.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/3/23
 * @Time: 23:56
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
@Controller
@RequestMapping("/admin/components")
public class ComponentsController extends BaseController {

    @Autowired
    private ComponentService componentService;

    @GetMapping("init")
    public String init(ModelMap modelMap) {
        modelMap.put("active", "layout");
        return "admin/views/layout/Components";
    }

    @GetMapping
    @ResponseBody
    public Result pages(Pageable pageable) {
        Page<Component> pageComponentAssociates = componentService.search(pageable);
        return page(pageComponentAssociates.getContent()).page(pageable.getPageSize()).size(pageable.getPageNumber()).
                totalCount(pageComponentAssociates.getTotalElements()).total(pageComponentAssociates.getTotalElements());
    }
}
