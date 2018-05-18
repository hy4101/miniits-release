package com.miniits.base.controller.admin;

import com.miniits.base.model.dto.AppPushDto;
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
    public Result push(@PathVariable(name = "id") String id, @RequestParam(name = "remark") String remark) throws Exception {
        Component component = componentService.findOne(id);
        User currentUser = (User) SecurityUtils.getSubject().getPrincipal();
        AppPushDto appPushDto = new AppPushDto();
        appPushDto.setComponent(component);
        appPushDto.setAuthorId(ObjectUtils.isEmpty(currentUser) ? null : currentUser.getId());
        appPushDto.setAuthorName(ObjectUtils.isEmpty(currentUser) ? null : currentUser.getUserName());
        appPushDto.setAppType(APP_TYPE_COMPONENT);
        appPushDto.setAppTypeName("组件");
        appPushDto.setRemark(remark);

        AppStore appStore = appStoreServer.save(ConvertUtil.toVO(appPushDto, AppStore.class));

        return success(appStore);
    }
}
