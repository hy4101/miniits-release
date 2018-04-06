package com.miniits.base.controller.admin;

import com.miniits.base.model.entity.Links;
import com.miniits.base.mysql.Pageable;
import com.miniits.base.service.LinksServer;
import com.miniits.base.utils.BaseController;
import com.miniits.base.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import static com.miniits.base.utils.SystemDict.GLOBAL_STATUS_YES;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/4/6
 * @Time: 16:54
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
@Controller
@RequestMapping("/admin/links")
public class LinksController extends BaseController {

    @Autowired
    private LinksServer linksServer;

    @GetMapping("init")
    public String init(ModelMap modelMap) {
        modelMap.put("active", "content");
        return "admin/views/content/Links";
    }

    @GetMapping
    @ResponseBody
    public Result pages(Pageable pageable) {
        Page<Links> links = linksServer.search(pageable);
        return page(links.getContent()).page(pageable.getPageSize()).size(pageable.getPageNumber()).
                totalCount(links.getTotalElements()).total(links.getTotalElements());
    }

    @PostMapping
    @ResponseBody
    public Result saveLink(Links links) {
        links.setStatus(GLOBAL_STATUS_YES);
        links.setStatusName("启用");
        return success(linksServer.save(links));
    }

    @DeleteMapping("{id}")
    @ResponseBody
    public Result pages(@PathVariable(value = "id") String id) {
        linksServer.delete(id);
        return success("删除成功");
    }

    @PostMapping("/change/status")
    @ResponseBody
    public Result changeStatus(@RequestParam(value = "id") String id, @RequestParam(value = "status") Integer status) {
        linksServer.changeStatus(id, status);
        return success("更改成功");
    }

}
