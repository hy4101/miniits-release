package com.miniits.base.controller.admin;

import com.miniits.base.service.ComponentImageServer;
import com.miniits.base.utils.BaseController;
import com.miniits.base.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/3/25
 * @Time: 21:52
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
@Controller
@RequestMapping("/admin/componentImages")
public class ComponentImageController extends BaseController {

    @Autowired
    private ComponentImageServer componentImageServer;

    @PostMapping("/change/status")
    @ResponseBody
    public Result changeStatus(@RequestParam(value = "id") String id, @RequestParam(value = "status") Integer status) {
        componentImageServer.changeStatus(id, status);
        return success("更改成功");
    }
}
