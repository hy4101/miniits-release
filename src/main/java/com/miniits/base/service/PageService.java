package com.miniits.base.service;

import com.miniits.base.dao.PageComponentAssociateRepository;
import com.miniits.base.dao.PageRepository;
import com.miniits.base.model.dto.SeoDTO;
import com.miniits.base.model.entity.ComponentImage;
import com.miniits.base.model.entity.Page;
import com.miniits.base.model.entity.PageComponentAssociate;
import com.miniits.base.mysql.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.miniits.base.utils.SystemDict.Component.COMPONENT_SOURCE_SYSTEM;
import static com.miniits.base.utils.SystemDict.GLOBAL_STATUS_YES;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/3/24
 * @Time: 20:29
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */

@Service
@Transactional
public class PageService extends BaseServiceImpl<Page, String> {

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private PageComponentAssociateRepository pageComponentAssociateRepository;

    @Autowired
    public void setBaseDao(PageRepository pageRepository) {
        super.setBaseDao(pageRepository);
    }


    public Page getPage(String pageName, Integer pageStatus) {
        return pageRepository.findByPageNameAndPageStatus(pageName, pageStatus);
    }

    /**
     * 初始化页面，生成 title, body, footer 默认组件
     *
     * @param page
     */
    public List<PageComponentAssociate> initPage(Page page) {
        long num = System.currentTimeMillis();
        List<PageComponentAssociate> pageComponentAssociate = new ArrayList<>();
        ComponentImage componentImage_1 = new ComponentImage();
        PageComponentAssociate pageComponentAssociate_1 = new PageComponentAssociate();
        componentImage_1.setComponentName(page.getPageName() + "_title");
        componentImage_1.setComponentId(++num + "");
        componentImage_1.setComponentSource(COMPONENT_SOURCE_SYSTEM);
        componentImage_1.setComponentSourceName("系统");
        componentImage_1.setComponentStatus(GLOBAL_STATUS_YES);
        componentImage_1.setComponentStatusName("启用");
        componentImage_1.setComponentBody("<div componentId=" + componentImage_1.getComponentId() + " componentName=" + page.getPageName() + "_title style='width:100%;height:auto' >" +
                "</div>");
        pageComponentAssociate_1.setSorts(1);
        pageComponentAssociate_1.setLevel(1);
        pageComponentAssociate_1.setComponentImage(componentImage_1);

        ComponentImage componentImage_2 = new ComponentImage();
        PageComponentAssociate pageComponentAssociate_2 = new PageComponentAssociate();
        componentImage_2.setComponentName(page.getPageName() + "_body");
        componentImage_2.setComponentId(++num + "");
        componentImage_2.setComponentSource(COMPONENT_SOURCE_SYSTEM);
        componentImage_2.setComponentSourceName("系统");
        componentImage_2.setComponentStatus(GLOBAL_STATUS_YES);
        componentImage_2.setComponentStatusName("启用");
        componentImage_2.setComponentBody("<div componentId=" + componentImage_2.getComponentId() + " componentName=" + page.getPageName() + "_body style='width:100%;height:auto' >" +
                "</div>");
        pageComponentAssociate_2.setSorts(2);
        pageComponentAssociate_2.setLevel(1);
        pageComponentAssociate_2.setComponentImage(componentImage_2);

        ComponentImage componentImage_3 = new ComponentImage();
        PageComponentAssociate pageComponentAssociate_3 = new PageComponentAssociate();
        componentImage_3.setComponentName(page.getPageName() + "_footer");
        componentImage_3.setComponentId(++num + "");
        componentImage_3.setComponentSource(COMPONENT_SOURCE_SYSTEM);
        componentImage_3.setComponentSourceName("系统");
        componentImage_3.setComponentStatus(GLOBAL_STATUS_YES);
        componentImage_3.setComponentStatusName("启用");
        componentImage_3.setComponentBody("<div componentId=" + componentImage_3.getComponentId() + " componentName=" + page.getPageName() + "_footer style='width:100%;height:auto' >" +
                "</div>");
        pageComponentAssociate_3.setSorts(3);
        pageComponentAssociate_3.setLevel(1);
        pageComponentAssociate_3.setComponentImage(componentImage_3);

        pageComponentAssociate.add(pageComponentAssociate_1);
        pageComponentAssociate.add(pageComponentAssociate_2);
        pageComponentAssociate.add(pageComponentAssociate_3);

        for (PageComponentAssociate componentAssociate : pageComponentAssociate) {
            componentAssociate.setPage(page);
        }
        return pageComponentAssociate;
    }

    @Override
    public void delete(String id) {
        if (!ObjectUtils.isEmpty(pageComponentAssociateRepository.findByPage_Id(id))) {
            pageComponentAssociateRepository.deleteByPage_Id(id);
        }
        pageRepository.delete(id);
    }

    @Override
    public Page save(Page page) {
        if (StringUtils.isEmpty(page.getPageStatus())) {
            page.setPageStatus(100000002);
            page.setPageStatusName("禁用");
        }
        if (StringUtils.isEmpty(page.getId())) {
            page.setPageComponentAssociates(initPage(page));
        }
        return pageRepository.save(page);
    }

    public void changeStatus(String id, Integer userStatusCode) {
        pageRepository.changeStatus(id, userStatusCode, userStatusCode.equals(GLOBAL_STATUS_YES) ? "启用" : "禁用");
    }

    public void setCreateHtmlFile(String id, Integer createStaticFile) {
        pageRepository.setCreateHtmlFile(id, createStaticFile);
    }

    public void setPageSeo(SeoDTO seoDTO) {
        pageRepository.setPageSeo(seoDTO.getId(), seoDTO.getKeywords(), seoDTO.getTitle(), seoDTO.getDescription());
    }

}
