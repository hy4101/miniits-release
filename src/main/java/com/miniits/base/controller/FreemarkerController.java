package com.miniits.base.controller;


import com.miniits.base.model.entity.User;
import com.miniits.base.model.vo.TestVO;
import com.miniits.base.model.vo.UserVO;
import com.miniits.base.mysql.Pageable;
import com.miniits.base.service.UserService;
import com.miniits.base.utils.BaseController;
import com.miniits.base.utils.ConvertUtil;
import com.miniits.base.utils.Result;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Controller
@RequestMapping("/admin/search-form")
public class FreemarkerController extends BaseController implements TemplateMethodModelEx {

    public FreemarkerController() {
    }

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseBody
    public Result sdf(Pageable pageable, String target) {
        Result result = null;
        switch (target) {
            case "LOG":
                Page<User> users = userService.search(pageable);
                List<UserVO> userVOS = (List<UserVO>) ConvertUtil.toVOS(users.getContent(), UserVO.class);
                result = page(userVOS).page(pageable.getPageSize()).size(pageable.getPageNumber()).totalCount(users.getTotalElements()).total(users.getTotalElements());
                break;
        }
        return result;
    }

    @Override
    public Object exec(List list) throws TemplateModelException {
        List<TestVO> testVOS = new ArrayList<>();
        return page(testVOS);
    }

}

