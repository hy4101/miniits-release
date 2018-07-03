package com.miniits.base.controller.admin;

import com.miniits.base.model.dto.AppPushDTO;
import com.miniits.base.model.entity.AppContent;
import com.miniits.base.model.entity.AppStore;
import com.miniits.base.model.entity.Component;
import com.miniits.base.service.AppStoreServer;
import com.miniits.base.service.ComponentService;
import com.miniits.base.utils.BaseController;
import com.miniits.base.utils.ConvertUtil;
import com.miniits.base.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

import static com.miniits.base.utils.SystemDict.AppStatus.APP_STATUS_ONLINE;
import static com.miniits.base.utils.SystemDict.AppType.APP_TYPE_COMPONENT;

/**
 * @author: wq
 * @Date: 2018/5/17
 * @Time: 9:52
 * <p>
 * Description:
 * 对外发布应用
 */
@Controller
@RequestMapping("/admin/push")
public class PushCompontenController extends BaseController {

    @Qualifier("restTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Value("${m-plus.url.push}")
    private String pushAppUrl;

    @Value("${domain.path}")
    private String name;

    @Autowired
    private ComponentService componentService;

    @Autowired
    private AppStoreServer appStoreServer;

    @PostMapping("/{id}")
    @ResponseBody
    public Result push(@PathVariable(name = "id") String id, AppPushDTO app) throws Exception {
        Component component = componentService.findOne(id);
        AppStore appStore = new AppStore();
        appStore.setAppContent(ConvertUtil.toVO(component, AppContent.class));
        appStore.getAppContent().setId(null);
        appStore.setAuthorName(app.getAuthorName());
        appStore.setAppType(APP_TYPE_COMPONENT);
        appStore.setAppTypeName("组件");
        appStore.setRemark(app.getRemark());
        appStore.setAppStatus(APP_STATUS_ONLINE);
        appStore.setAppStatusName("在线");
        appStore.setUpTime(new Date());
        appStore.setAppImageUrl(app.getAppImageUrl());
        appStore.setTags(app.getTags());
        appStore.setAscriptionNumber(name + "_N" + component.getComponentId());
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> httpEntity = new HttpEntity<String>(toJson(appStore), headers);

        Result result = restTemplate.postForObject(pushAppUrl, httpEntity, Result.class);
        return result;
    }
}
