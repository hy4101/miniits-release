package com.miniits.base.controller.admin;

import com.miniits.base.model.vo.TodayDataVO;
import com.miniits.base.service.ArticleServer;
import com.miniits.base.service.ImageServer;
import com.miniits.base.service.UserService;
import com.miniits.base.utils.BaseController;
import com.miniits.base.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/6/17
 * @Time: 13:49
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
@Controller
@RequestMapping("/admin/dashboard")
public class DashboardController extends BaseController {

    @Autowired
    private ArticleServer articleServer;

    @Autowired
    private ImageServer imageServer;

    @Autowired
    private UserService userService;

    @GetMapping("init")
    public String init(ModelMap modelMap) {
        String filters = "GTE_createDate=" + getStringDate() + " 00:00:00;LTE_createDate=" + getStringDate() + " 23:59:59";
        long article = articleServer.count(filters);
        long image = imageServer.count(filters);
        long user = userService.count(filters);
        modelMap.put("active", "dashboard");
        modelMap.put("articleNum", article);
        modelMap.put("imageNum", image);
        modelMap.put("userNum", user);
        return "admin/views/system/Dashboard";
    }

    @GetMapping("line-data")
    @ResponseBody
    public Result lineData(@RequestParam(value = "filters") String filters) {
        long article = articleServer.count(filters);
        long image = imageServer.count(filters);
        long user = userService.count(filters);
        return success(new TodayDataVO(article, image, user));
    }

    public String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

}
