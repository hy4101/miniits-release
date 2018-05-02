package com.miniits.base.controller.admin;

import com.miniits.base.model.entity.ComponentImage;
import com.miniits.base.model.entity.PageComponentAssociate;
import com.miniits.base.model.vo.ComponentImageVO;
import com.miniits.base.model.vo.PageComponentAssociateVO;
import com.miniits.base.mysql.Pageable;
import com.miniits.base.service.PageComponentAssociateService;
import com.miniits.base.utils.BaseController;
import com.miniits.base.utils.ConvertUtil;
import com.miniits.base.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import static com.miniits.base.utils.SystemDict.Component.COMPONENT_SOURCE_DIY;


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

            for (PageComponentAssociate pageComponentAssociate : pageComponentAssociates.getContent()) {
                if (!ObjectUtils.isEmpty(pageComponentAssociate.getComponentImagePId()) && pageComponentAssociate.getComponentImage().getId()
                        .equals(pageComponentAssociate.getComponentImagePId().getId())) {
                    pageComponentAssociateVO.setComponentImagePIdVO(ConvertUtil.toVO(pageComponentAssociate.getComponentImagePId(), ComponentImageVO.class));
                }
            }

            pageComponentAssociateVO.setComponentImageVO(ConvertUtil.toVO(componentImage, ComponentImageVO.class));
        });
        return page(pageComponentAssociateVOS).page(pageable.getPageSize()).size(pageable.getPageNumber()).
                totalCount(pageComponentAssociates.getTotalElements()).total(pageComponentAssociates.getTotalElements());
    }

    @PostMapping(value = "/save")
    @ResponseBody
    public Result save(@RequestParam(value = "jsonPage") String jsonPage,
                       @RequestParam(value = "jsonComponentImagePId") String jsonComponentImagePId,
                       @RequestParam(value = "jsonComponentImages") String jsonComponentImages) throws Exception {
        com.miniits.base.model.entity.Page page = toEntity(jsonPage, com.miniits.base.model.entity.Page.class);
        ComponentImage componentImagePId = toEntity(jsonComponentImagePId, ComponentImage.class);
        PageComponentAssociate pca = pageComponentAssociateService.findFirstBycomponentAndComponentImagePId_Id(componentImagePId.getId());
        PageComponentAssociate pcac = pageComponentAssociateService.findFirstByComponentImagePId_IdOrderBySortsDesc(componentImagePId.getId());

        AtomicBoolean bo = new AtomicBoolean(false);
        final Integer[] sort = {ObjectUtils.isEmpty(pcac) ? Integer.valueOf(pca.getSorts().toString() + "1") : pcac.getSorts()};
        List<ComponentImage> componentImages = (List<ComponentImage>) toEntitys(jsonComponentImages, ComponentImage.class);
        List<PageComponentAssociate> pageComponentAssociates = new ArrayList<>();
        componentImages.forEach(componentImage -> {
            componentImage.setId(null);
            componentImage.setComponentSource(COMPONENT_SOURCE_DIY);
            componentImage.setComponentSourceName("DIY");
            PageComponentAssociate pageComponentAssociate = new PageComponentAssociate();
            pageComponentAssociate.setPage(page);
            pageComponentAssociate.setComponentImagePId(componentImagePId);
            pageComponentAssociate.setComponentImage(componentImage);
            pageComponentAssociate.setLevel(pca.getLevel() + 1);
            Integer[] s = sort;
            if (!ObjectUtils.isEmpty(pcac) || bo.get()) {
                s[0] = ++sort[0];
            }
            bo.set(true);
            pageComponentAssociate.setSorts(s[0]);

            pageComponentAssociates.add(pageComponentAssociate);
        });

        return page(pageComponentAssociateService.save(pageComponentAssociates));
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    public Result delete(@PathVariable(value = "id") String id) {
        pageComponentAssociateService.delete(id);
        return success("组件移除成功");
    }

    @PostMapping(value = "/revision-sort/{id}/{type}")
    @ResponseBody
    public Result revisionSort(@PathVariable(value = "id") String id, @PathVariable(value = "type") String type) {
        PageComponentAssociate pageComponentAssociate = pageComponentAssociateService.findOne(id);
        Integer sort = pageComponentAssociate.getSorts() - 1;
        if (type.equals("down")) {
            sort = pageComponentAssociate.getSorts() + 1;
        }
        PageComponentAssociate ppcoa = null;
        if (pageComponentAssociate.getLevel().equals(1)) {
            ppcoa = pageComponentAssociateService.findByPage_IdAndLevelAndSorts(pageComponentAssociate.getPage().getId(), pageComponentAssociate.getLevel(), sort);
        } else {
            ppcoa = pageComponentAssociateService.findByComponentImagePId_IdAndLevelAndSorts(pageComponentAssociate.getComponentImagePId().getId(), pageComponentAssociate.getLevel(), sort);
        }

        if (ObjectUtils.isEmpty(ppcoa)) {
            String mes = type.equals("down") ? "当前组件在父组件容器内已经是 【 置底 】 状态，无需调整" : "当前组件在父组件容器内已经是 【 置顶 】 状态，无需调整";
            return error(mes);
        }

        if (!ObjectUtils.isEmpty(pageComponentAssociate.getComponentImagePId()) && !ObjectUtils.isEmpty(ppcoa.getComponentImagePId()) && !pageComponentAssociate.getComponentImagePId().getId().equals(ppcoa.getComponentImagePId().getId())) {
            return error("当前组件和上一个组件不在同一个父组件容器内，无法调整！你可以尝试删除组件后重新添加");
        }

        if (type.equals("down")) {
            pageComponentAssociateService.revisionSort(pageComponentAssociate.getId(), pageComponentAssociate.getSorts() + 1);
            pageComponentAssociateService.revisionSort(ppcoa.getId(), ppcoa.getSorts() - 1);
        } else {
            pageComponentAssociateService.revisionSort(pageComponentAssociate.getId(), pageComponentAssociate.getSorts() - 1);
            pageComponentAssociateService.revisionSort(ppcoa.getId(), ppcoa.getSorts() + 1);
        }

        return success("组件调整成功");
    }

}
