package com.miniits.base.controller.admin;

import com.miniits.base.model.entity.Component;
import com.miniits.base.mysql.Pageable;
import com.miniits.base.service.ComponentService;
import com.miniits.base.utils.BaseController;
import com.miniits.base.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
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

    @Autowired
    private ComponentService componentService;

    @Value("${m-plus.url.apps}")
    private String apps;

    @Value("${m-plus.url.app}")
    private String app;

    @GetMapping("init")
    public String init(ModelMap modelMap, Pageable pageable) {
        Result appStoresVOResult = new Result();
        try {
            appStoresVOResult = restTemplate.getForObject(apps, Result.class, pageable.getFilters(), pageable.getSorts(), pageable.getPageSize(), pageable.getPageNumber());
        } catch (RestClientException e) {
            e.printStackTrace();
        }

        modelMap.put("active", "layout");
        modelMap.put("thisPageNumber", pageable.getPageNumber());
        modelMap.put("pageNumbers", getPageNumber(appStoresVOResult.getTotal(), pageable));
        modelMap.put("totalPageNumber", totalPage);
        modelMap.put("apps", appStoresVOResult.getRows());
        return "admin/views/layout/AppStore";
    }

    @GetMapping("get-app")
    @ResponseBody
    public Result getApp(@RequestParam(value = "am") String am,
                         @RequestParam(value = "sm") String sm) throws Exception {
        Result result = restTemplate.getForObject(app, Result.class, am, sm);
        if (!result.isSuccess()) {
            return error("获取失败");
        }
        componentService.save(toEntity(toJson(result.getObject()), Component.class));
        return success("获取成功,您可在组件管理中查看");
    }

}
