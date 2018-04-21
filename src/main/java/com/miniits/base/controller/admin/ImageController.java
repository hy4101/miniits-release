package com.miniits.base.controller.admin;

import com.miniits.base.model.entity.Image;
import com.miniits.base.mysql.Pageable;
import com.miniits.base.service.ImageServer;
import com.miniits.base.utils.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Autowired
    private ImageServer imageServer;

    @GetMapping("init")
    public String init(ModelMap modelMap, Pageable pageable) {
        Page<Image> images = imageServer.search(pageable);
        modelMap.put("active", "content");
        modelMap.put("images", images.getContent());
        return "admin/views/content/Images";
//        modelMap.put("active", "content");
//        return "admin/views/content/Images";
    }

    @GetMapping
    @ResponseBody
    public String pages(ModelMap modelMap, Pageable pageable) {
        Page<Image> images = imageServer.search(pageable);
        modelMap.put("active", "content");
        modelMap.put("images", page(images.getContent()).page(pageable.getPageSize()).size(pageable.getPageNumber()).
                totalCount(images.getTotalElements()).total(images.getTotalElements()));
        return "admin/views/content/Images";
    }

}
