package com.miniits.base.controller.admin;

import com.miniits.base.utils.gather.service.ImageCrawlerService;
import com.miniits.base.model.entity.Image;
import com.miniits.base.mysql.Pageable;
import com.miniits.base.service.ImageServer;
import com.miniits.base.utils.BaseController;
import com.miniits.base.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.miniits.base.utils.CommonUtil.getPageNumber;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/4/20
 * @Time: 22:43
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
@Controller
@RequestMapping("/admin/images")
public class ImageController extends BaseController {

    private Long totalPage = 0L;

    @Autowired
    private ImageServer imageServer;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private ImageCrawlerService imageCrawlerService;

    @GetMapping("init")
    public String init(ModelMap modelMap, Pageable pageable) {
        pageable = filterCache(pageable);
        Page<Image> images = imageServer.search(pageable);
        modelMap.put("active", "content");
        modelMap.put("thisPageNumber", pageable.getPageNumber());
        modelMap.put("pageNumbers", getPageNumber(images, pageable));
        modelMap.put("totalPageNumber", totalPage);
        modelMap.put("images", images.getContent());
        return "admin/views/content/Images";
    }

    private Pageable filterCache(Pageable pageable) {
        Object filters = httpSession.getAttribute("filters");
        if (StringUtils.isNotEmpty(pageable.getFilters())) {
            httpSession.setAttribute("filters", pageable.getFilters());
        } else if (!org.springframework.util.ObjectUtils.isEmpty(filters)) {
            pageable.setFilters(String.valueOf(filters));
        }
        return pageable;
    }

    @PostMapping("reset/filters-cache")
    @ResponseBody
    private Result resetFilterCache() {
        httpSession.removeAttribute("filters");
        return success("清除成功");
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Result getImage(@PathVariable(value = "id") String id) {
        return success(imageServer.findOne(id));
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Result deleteImage(@PathVariable(value = "id") String id) {
        imageServer.delete(id);
        return success("删除成功");
    }

    @PostMapping("/upload")
    @ResponseBody
    public Result sdf(@RequestParam(value = "fileUrl") String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            return error("url为空");
        }
        List<String> urls = new ArrayList<>();
        if (fileUrl.indexOf("][img]") != -1) {
            urls = Arrays.asList(fileUrl.split("\n")).stream().map(url -> {
                return url.split("\\]\\[img\\]")[0].substring(5);
            }).collect(Collectors.toList());
        } else {
            urls = Arrays.asList(fileUrl.split("\n"));
        }
        imageCrawlerService.imagesUpload(urls);
        return success("保存成功");
    }
}
