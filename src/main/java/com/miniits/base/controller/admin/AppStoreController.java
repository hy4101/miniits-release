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
import org.springframework.web.client.RestTemplate;

import static com.miniits.base.utils.CommonUtil.getPageNumber;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/6/21
 * @Time: 23:28
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
@Controller
@RequestMapping("/admin/app-store")
public class AppStoreController extends BaseController {

    private Long totalPage = 0L;

    @Autowired
    private ImageServer imageServer;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("init")
    public String init(ModelMap modelMap, Pageable pageable) {
        modelMap.put("active", "layout");
        Page<Image> images = imageServer.search(pageable);
        modelMap.put("thisPageNumber", pageable.getPageNumber());
        modelMap.put("pageNumbers", getPageNumber(images, pageable));
        modelMap.put("totalPageNumber", totalPage);
        modelMap.put("images", images.getContent());
        return "admin/views/layout/AppStore";
    }

}
