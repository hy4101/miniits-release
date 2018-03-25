package com.miniits.base.controller.admin;

import com.miniits.base.model.entity.ComponentImage;
import com.miniits.base.model.entity.PageComponentAssociate;
import com.miniits.base.model.vo.ComponentVO;
import com.miniits.base.model.vo.PageComponentAssociateVO;
import com.miniits.base.mysql.Pageable;
import com.miniits.base.service.PageComponentAssociateService;
import com.miniits.base.utils.BaseController;
import com.miniits.base.utils.ConvertUtil;
import com.miniits.base.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/3/24
 * @Time: 22:34
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
@Controller
@RequestMapping("/admin/page-component-associate")
public class PageComponentAssociateController extends BaseController {

    @Autowired
    private PageComponentAssociateService pageComponentAssociateService;

    @GetMapping
    @ResponseBody
    public Result pages(Pageable pageable) {
        Page<PageComponentAssociate> pageComponentAssociates = pageComponentAssociateService.search(pageable);
        List<PageComponentAssociateVO> pageComponentAssociateVOS = (List<PageComponentAssociateVO>) ConvertUtil.toVOS(pageComponentAssociates.getContent(), PageComponentAssociateVO.class);
        pageComponentAssociateVOS.forEach(pageComponentAssociateVO -> {
            ComponentImage componentImage = pageComponentAssociates.getContent().stream().filter(pageComponentAssociate -> pageComponentAssociate.getId()
                    .equals(pageComponentAssociateVO.getId())).collect(Collectors.toList()).get(0).getComponentImage();
            pageComponentAssociateVO.setComponentVO(ConvertUtil.toVO(componentImage, ComponentVO.class));
        });
        return page(pageComponentAssociateVOS).page(pageable.getPageSize()).size(pageable.getPageNumber()).
                totalCount(pageComponentAssociates.getTotalElements()).total(pageComponentAssociates.getTotalElements());
    }

}
