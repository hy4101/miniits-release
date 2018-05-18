package com.miniits.base.controller.admin;

import com.miniits.base.model.entity.AppContent;
import com.miniits.base.model.entity.AppStore;
import com.miniits.base.model.entity.Component;
import com.miniits.base.model.entity.User;
import com.miniits.base.service.AppStoreServer;
import com.miniits.base.service.ComponentService;
import com.miniits.base.utils.BaseController;
import com.miniits.base.utils.ConvertUtil;
import com.miniits.base.utils.Result;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static com.miniits.base.utils.SystemDict.AppStatus.APP_STATUS_ONLINE;
import static com.miniits.base.utils.SystemDict.AppType.APP_TYPE_COMPONENT;

/**
 * @author: wq
 * @Date: 2018/5/17
 * @Time: 9:52
 * <p>
 * Description:
 * 发布应用
 */
@Controller
@RequestMapping("/admin/push")
public class PushCompontenController extends BaseController {

    @Autowired
    private ComponentService componentService;

    @Autowired
    private AppStoreServer appStoreServer;

    @PostMapping("/{id}")
    @ResponseBody
    public Result push(@PathVariable(name = "id") String id, @RequestParam(name = "remark") String remark) {
        Component component = componentService.findOne(id);
        User currentUser = (User) SecurityUtils.getSubject().getPrincipal();
        AppStore appStore = new AppStore();
        appStore.setAppContent(ConvertUtil.toVO(component, AppContent.class));
        appStore.getAppContent().setId(null);
        appStore.setAuthorId(ObjectUtils.isEmpty(currentUser) ? "" : currentUser.getId());
        appStore.setAuthorName(ObjectUtils.isEmpty(currentUser) ? "" : currentUser.getUserName());
        appStore.setAppType(APP_TYPE_COMPONENT);
        appStore.setAppTypeName("组件");
        appStore.setRemark(remark);
        appStore.setAppStatus(APP_STATUS_ONLINE);
        appStore.setAppStatusName("在线");
        appStore.setUpTime(new Date());

        appStore = appStoreServer.save(appStore);

        return success(appStore);
    }
}
