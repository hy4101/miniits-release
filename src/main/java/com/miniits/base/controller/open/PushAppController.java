package com.miniits.base.controller.open;

import com.miniits.base.model.dto.AppPushDto;
import com.miniits.base.model.entity.AppStore;
import com.miniits.base.service.AppStoreServer;
import com.miniits.base.utils.BaseController;
import com.miniits.base.utils.ConvertUtil;
import com.miniits.base.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.miniits.base.utils.SystemDict.AppStatus.APP_STATUS_ONLINE;

/**
 * @author: wq
 * @Date: 2018/5/16
 * @Time: 16:51
 * <p>
 * Description:
 * ***
 */
@RestController
@RequestMapping("/miniits/app")
public class PushAppController extends BaseController {

    @Autowired
    private AppStoreServer appStoreServer;

    @PostMapping("/push")
    public Result push(@RequestParam(name = "app") String appPushDto) throws Exception {
        AppStore appStore = toEntity(appPushDto, AppStore.class);
        appStore.setAppStatus(APP_STATUS_ONLINE);
        appStore.setAppStatusName("在线");
        appStoreServer.save(appStore);
        return success("应用发布成功");
    }

    @GetMapping("/apps")
    public Result apps(@RequestBody AppPushDto appPushDto) {
        appStoreServer.save(ConvertUtil.toVO(appPushDto, AppStore.class));
        return success("应用发布成功");
    }

}
