package com.miniits.base.controller.open;

import com.miniits.base.utils.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wq
 * @Date: 2018/5/16
 * @Time: 16:51
 * <p>
 * Description:
 * 官方对外提供的api
 */
@RestController
@RequestMapping("/miniits/app")
public class PushAppController extends BaseController {



//    @GetMapping("/apps")
//    public Result apps(@RequestBody AppPushDTO appPushDto) {
//        appStoreServer.save(ConvertUtil.toVO(appPushDto, AppStore.class));
//        return success("应用发布成功");
//    }

}
