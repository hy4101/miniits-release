package com.miniits.base.controller.admin;

import com.miniits.base.model.entity.Component;
import com.miniits.base.model.entity.ComponentImage;
import com.miniits.base.mysql.Pageable;
import com.miniits.base.service.ComponentImageServer;
import com.miniits.base.service.ComponentService;
import com.miniits.base.utils.BaseController;
import com.miniits.base.utils.ConvertUtil;
import com.miniits.base.utils.Result;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.miniits.base.utils.SystemDict.*;

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

    @Autowired
    private ComponentImageServer componentImageServer;

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
        String type = component.getSaveType();
        if (!StringUtils.isEmpty(component.getId())) {
            //修改
            Component old = componentService.findOne(component.getId());
            old.setComponentBodyApi(component.getComponentBodyApi());
            old.setApiDataStructureType(component.getApiDataStructureType());
            old.setDataFilters(component.getDataFilters());
            old.setComponentName(component.getComponentName());
            old.setComponentBody(component.getComponentBody());
            component = old;
        } else {
            //新增
            component.setComponentId(System.currentTimeMillis() + "");
            component.setComponentStatus(GLOBAL_STATUS_YES);
            component.setComponentStatusName("启用");
            //处理分页组件
            Document document = Jsoup.parse(component.getComponentBody());
            if (component.getDataFilters().indexOf("pageSize=") >= 0 && !document.select("nav.miniits-page-component").toString().isEmpty()) {
                component.setApiDataStructureType(API_DATA_STRUCTURE_TYPE_PAGE);
            }
            if (component.getDataFilters().indexOf("pageSize=") >= 0 && document.select("nav.miniits-page-component").toString().isEmpty()) {
                component.setApiDataStructureType(API_DATA_STRUCTURE_TYPE_NO_PAGE);
            }
        }

        if (!StringUtils.isEmpty(type) && type.equals("save_article_update_page_btn")) {
            List<ComponentImage> componentImageList = componentImageServer.findByComponentReferenceId(component.getId());
            for (ComponentImage componentImage : componentImageList) {
                componentImage.setComponentBodyApi(component.getComponentBodyApi());
                componentImage.setApiDataStructureType(component.getApiDataStructureType());
                componentImage.setDataFilters(component.getDataFilters());
                componentImage.setComponentName(component.getComponentName());
                componentImage.setComponentBody(component.getComponentBody());
                componentImageServer.save(componentImage);
            }
        }
        return success(componentService.save(component));
    }

    @PostMapping("copy-development/{id}")
    @ResponseBody
    public Result copyDevelopment(ModelMap modelMap, @PathVariable(value = "id") String id) {
        modelMap.put("active", "layout");
        if (!StringUtils.isEmpty(id)) {
            Component component = componentService.findOne(id);
            Component copy = ConvertUtil.toVO(component, Component.class);
            copy.setId(null);
            copy.setComponentId(System.currentTimeMillis() + "");
            copy.setComponentName(component.getComponentName() + " -副本");
            copy.setComponentStatus(GLOBAL_STATUS_NO);
            copy.setComponentStatusName("禁用");
            componentService.save(copy);
        }
        return success("复制成功");
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
