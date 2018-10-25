package com.miniits.base.controller.admin;

import com.miniits.base.model.dto.SeoDTO;
import com.miniits.base.model.vo.PageVO;
import com.miniits.base.model.vo.UserVO;
import com.miniits.base.mysql.Pageable;
import com.miniits.base.service.PageService;
import com.miniits.base.utils.BaseController;
import com.miniits.base.utils.ConvertUtil;
import com.miniits.base.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.miniits.base.utils.FileUtil.deletefile;
import static com.miniits.base.utils.MD5Util.hashStr;
import static com.miniits.base.utils.RequestUtil.getPath;
import static com.miniits.base.utils.SystemDict.GLOBAL_STATUS_NO;
import static com.miniits.base.utils.SystemDict.GLOBAL_STATUS_YES;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(PageController.class);

    @Value("${domain.path}")
    private String rootPath;

    @Autowired
    private PageService pageService;

    @GetMapping("init")
    public String init(ModelMap modelMap) {
        modelMap.put("active", "layout");
        modelMap.put("rootPath", rootPath);
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

    @PostMapping("/save")
    @ResponseBody
    public Result save(com.miniits.base.model.entity.Page page) {
        if (page.getPageStatus().equals(GLOBAL_STATUS_YES)) {
            com.miniits.base.model.entity.Page p = pageService.getPage(page.getPageName(), GLOBAL_STATUS_YES, 200);
            if (!ObjectUtils.isEmpty(p)) {
                return error("你有一个【 " + page.getPageName() + " 】页面为启用状态，相同的页面文件只能有一个为启用状态，你可以选择禁用后重试！");
            }
        }
        return success(ConvertUtil.toVO(pageService.save(page), UserVO.class));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Result deleteUser(@PathVariable(value = "id") String id) {
        pageService.delete(id);
        return success("页面删除成功");
    }

    @PostMapping("/change/status")
    @ResponseBody
    public Result changeStatus(@RequestParam(value = "id") String id, @RequestParam(value = "page_name") String pageName, @RequestParam(value = "status") Integer status) {
        if (status.equals(GLOBAL_STATUS_YES)) {
            com.miniits.base.model.entity.Page page = pageService.getPage(pageName, GLOBAL_STATUS_YES, 200);
            if (!ObjectUtils.isEmpty(page) && page.getPageStatus().equals(GLOBAL_STATUS_YES)) {
                return error("你有一个【 " + page.getPageName() + " 】页面为启用状态，相同的页面文件只能有一个为启用状态，你可以选择禁用后重试！");
            }
        }
        pageService.changeStatus(id, status);
        return success("更改成功");
    }

    /**
     * 设置是否生成静态文件
     * html
     *
     * @param id
     * @param fileName
     * @param createStaticFile
     * @return
     */
    @PostMapping("/setting-page-create-html")
    @ResponseBody
    public Result setCreateHtmlFile(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "file_name") String fileName,
            @RequestParam(value = "create_static_file") Integer createStaticFile) {
        if (createStaticFile.equals(GLOBAL_STATUS_NO)) {
            String pn = hashStr(fileName);
            String path = getPath("templates") + "/customize/" + pn + "/";
            if (!fileName.equals("index")) {
                path = getPath("templates") + "/customize/" + pn;
            }
            LOGGER.warn("正在删除文件...", fileName, pn);
            deletefile(path);
        }
        pageService.setCreateHtmlFile(id, createStaticFile);
        return success("更改成功");
    }

    @PostMapping("/setting/seo")
    @ResponseBody
    public Result setSeo(SeoDTO seoDTO) {
        seoDTO.setKeywords(seoDTO.getKeywords().replaceAll("，", ","));
        pageService.setPageSeo(seoDTO);
        return success("设置成功");
    }

    @PostMapping("/setting/templates-caching")
    @ResponseBody
    public Result setSeo(@RequestParam("id") String id, @RequestParam("caching") Integer caching, @RequestParam("pageName") String pageName) {
        if (caching.equals(GLOBAL_STATUS_NO)) {
            String path = getPath("templates/") + "ftl-" + pageName + ".ftl";
            deletefile(path);
        }
        pageService.changeTemplateCaching(id, caching);
        return success("设置成功");
    }

    @GetMapping("/access-page/{pathName}")
    public String redirect(@PathVariable(value = "pathName") String pathName) {
        return "redirect:/" + rootPath + "/" + pathName;
    }

}
