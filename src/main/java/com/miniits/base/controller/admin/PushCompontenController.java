package com.miniits.base.controller.admin;

import com.miniits.base.model.dto.AppPushDTO;
import com.miniits.base.model.entity.Component;
import com.miniits.base.model.entity.User;
import com.miniits.base.service.ComponentService;
import com.miniits.base.utils.BaseController;
import com.miniits.base.utils.Result;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

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
    private RestTemplate restTemplate;

    @Autowired
    private ComponentService componentService;

    @PostMapping("/{id}")
    @ResponseBody
    public Result push(@PathVariable(name = "id") String id, @RequestParam(name = "remark") String remark) throws Exception {
        Component component = componentService.findOne(id);
        User currentUser = (User) SecurityUtils.getSubject().getPrincipal();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
//        params.add("app", toJson(component));
//        params.add("authorId", ObjectUtils.isEmpty(currentUser) ? null : currentUser.getId());
//        params.add("authorName", ObjectUtils.isEmpty(currentUser) ? null : currentUser.getUserName());
//        params.add("appType", APP_TYPE_COMPONENT.toString());
//        params.add("appTypeName", "组件");

        AppPushDTO appPushDto = new AppPushDTO();
        appPushDto.setComponent(component);
        appPushDto.setAuthorId(ObjectUtils.isEmpty(currentUser) ? "AuthorId" : currentUser.getId());
        appPushDto.setAuthorName(ObjectUtils.isEmpty(currentUser) ? "AuthorName" : currentUser.getUserName());
        appPushDto.setAppType(APP_TYPE_COMPONENT);
        appPushDto.setAppTypeName("组件");
        appPushDto.setRemark(remark);
        appPushDto.setUpTime(new Date());
        params.add("app", toJson(appPushDto));
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8001/miniits/app/push", requestEntity, String.class);
        return null;
    }
}
