package com.miniits.base.controller.admin;

import com.miniits.base.mysql.Pageable;
import com.miniits.base.utils.BaseController;
import com.miniits.base.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Qualifier("restTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("init")
    public String init(ModelMap modelMap, Pageable pageable) {
        Result appStoresVOResult = restTemplate.getForObject("http://localhost:8000/miniits/apps/apps?filters={filters}&sorts={sorts}&pageSize={pageSize}&pageNumber={pageNumber}",
                Result.class, pageable.getFilters(), pageable.getSorts(), pageable.getPageSize(), pageable.getPageNumber());
        modelMap.put("active", "layout");
        modelMap.put("thisPageNumber", pageable.getPageNumber());
        modelMap.put("pageNumbers", getPageNumber(appStoresVOResult.getTotal(), pageable));
        modelMap.put("totalPageNumber", totalPage);
        modelMap.put("apps", appStoresVOResult.getRows());
        return "admin/views/layout/AppStore";
    }

}
