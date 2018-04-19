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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import static com.miniits.base.utils.SystemDict.GLOBAL_STATUS_YES;

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
public class ComponentController extends BaseController {

    @Autowired
    private ComponentService componentService;

    @GetMapping("init")
    public String init(ModelMap modelMap) {
        modelMap.put("active", "layout");
        return "admin/views/layout/Components";
    }

    @GetMapping("init-development")
    public String initDevelopment(ModelMap modelMap) {
        modelMap.put("active", "layout");
        return "admin/views/layout/ComponentsDev";
    }

    @GetMapping("init-development-code")
    public String initDevelopmentCode(ModelMap modelMap) {
        modelMap.put("active", "layout");
        return "admin/views/layout/ComponentsDevCode";
    }

    @GetMapping("modify-development/{id}")
    public String modifyDevelopment(ModelMap modelMap, @PathVariable(value = "id") String id) {
        modelMap.put("active", "layout");
        if (!StringUtils.isEmpty(id)) {
            Component component = componentService.findOne(id);
            component.setFilters(component.getApiDataStructureType() + "=" + component.getComponentBodyApi());
            modelMap.put("development", component);
        }
        return "admin/views/layout/ComponentsDevCode";
    }

    @GetMapping
    @ResponseBody
    public Result pages(Pageable pageable) {
        Page<Component> pageComponentAssociates = componentService.search(pageable);
        return page(pageComponentAssociates.getContent()).page(pageable.getPageSize()).size(pageable.getPageNumber()).
                totalCount(pageComponentAssociates.getTotalElements()).total(pageComponentAssociates.getTotalElements());
    }

    @PostMapping("/save")
    @ResponseBody
    public Result saveUser(Component component) {
        if (!StringUtils.isEmpty(component.getId())) {
            Component old = componentService.findOne(component.getId());
            old.setComponentBodyApi(component.getComponentBodyApi());
            old.setApiDataStructureType(component.getApiDataStructureType());
            old.setDataFilters(component.getDataFilters());
            old.setComponentName(component.getComponentName());
            old.setComponentBody(component.getComponentBody());
            component = old;
        } else {
            component.setComponentId(System.currentTimeMillis() + "");
            component.setComponentStatus(GLOBAL_STATUS_YES);
            component.setComponentStatusName("启用");
        }
        return success(componentService.save(component));
    }

    @DeleteMapping("{id}")
    @ResponseBody
    public Result pages(@PathVariable(value = "id") String id) {
        componentService.delete(id);
        return success("删除成功");
    }

    @PostMapping("/change/status")
    @ResponseBody
    public Result changeStatus(@RequestParam(value = "id") String id, @RequestParam(value = "status") Integer status) {
        componentService.changeStatus(id, status);
        return success("更改成功");
    }

}
