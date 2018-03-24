package com.miniits.base.controller.admin;

import com.miniits.base.model.vo.PageVO;
import com.miniits.base.mysql.Pageable;
import com.miniits.base.service.PageService;
import com.miniits.base.utils.BaseController;
import com.miniits.base.utils.ConvertUtil;
import com.miniits.base.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/3/23
 * @Time: 23:48
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
@Controller
@RequestMapping("/admin/pages")
public class PageController extends BaseController {

    @Autowired
    private PageService pageService;

    @GetMapping("init")
    public String init(ModelMap modelMap) {
        modelMap.put("active", "layout");
        return "admin/views/layout/Pages";
    }

    @GetMapping
    @ResponseBody
    public Result pages(Pageable pageable) {
        Page<com.miniits.base.model.entity.Page> pages = pageService.search(pageable);
        List<PageVO> pageVOS = (List<PageVO>) ConvertUtil.toVOS(pages.getContent(), PageVO.class);
        return page(pageVOS).page(pageable.getPageSize()).size(pageable.getPageNumber()).
                totalCount(pages.getTotalElements()).total(pages.getTotalElements());
    }

}
