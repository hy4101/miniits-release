package com.miniits.base.controller.open;

import com.miniits.base.model.entity.ComponentImage;
import com.miniits.base.model.entity.Page;
import com.miniits.base.model.entity.PageComponentAssociate;
import com.miniits.base.mysql.Pageable;
import com.miniits.base.service.PageService;
import com.miniits.base.utils.ComponentImageAndDocument;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import java.util.stream.Collectors;

import static com.miniits.base.utils.CommonUtil.randomStr;
import static com.miniits.base.utils.DataUtil.filters;
import static com.miniits.base.utils.DataUtil.getData;
import static com.miniits.base.utils.HTMLUtil.createHtml;
import static com.miniits.base.utils.HTMLUtil.createTemplateFile;
import static com.miniits.base.utils.HTMLUtil.perfectHtml;
import static com.miniits.base.utils.SystemDict.API_DATA_STRUCTURE_TYPES;
import static com.miniits.base.utils.SystemFile.isPackageExist;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/3/16
 * @Time: 22:13
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
@Controller
@RequestMapping()
public class IndexController {

    @Autowired
    private PageService pageService;

    @GetMapping
    public String test(ModelMap modelMap) {
        modelMap.put("title", "the is title");
        modelMap.put("content", "HotArticles");
        modelMap.put("description", "the is description");
        modelMap.put("keywords", "the is keywords");
        return "redirect:/index";
    }

    @GetMapping("/{path}")
    public String path(
            @PathVariable(value = "path") String path,
            ModelMap modelMap) {
        return "default/Article-Detail";
    }

    @GetMapping(value = {"index", "index.html"})
    public String index(ModelMap modelMap) {
        ComponentImageAndDocument componentImageAndDocument = mergePage(modelMap);
        createTemplateFile("ftl-index", componentImageAndDocument.getDocument().toString()
                .replaceAll("<!--#list-->", "</#list>")
                .replaceAll("&lt;", "<")
                .replaceAll("&gt;", ">"));
        modelMap = componentImageAndDocument.getModelMap();
        Map<String, Object> map = renderingPage();
        map.put("uXppmcPtoMxFeweSdZnJKList", modelMap.get("uXppmcPtoMxFeweSdZnJKList"));
        createHtml(map);
        return "index";
    }

    private Map<String, Object> renderingPage() {
        isPackageExist(this.getClass().getResource("/templates/").getPath() + "customize/");
        String path = this.getClass().getResource("/templates/customize/").getPath();
        Map<String, Object> map = new HashMap<>();
        map.put("path", path);
        map.put("templateName", "ftl-index.ftl");
        map.put("fileName", "index.html");
        return map;
    }

    /**
     * 获取该页面的所有组件
     * 组件在父组件内的排序
     * 组件合并页面
     *
     * @return 组件列表和结构页面
     */
    private ComponentImageAndDocument mergePage(ModelMap modelMap) {
        StringBuffer html = new StringBuffer();
        Page page = pageService.getPage("index", 100000001);
        List<PageComponentAssociate> pageComponentAssociates = page.getPageComponentAssociates().stream()
                .filter(pca -> pca.getComponentImage().getComponentStatus().equals(100000001))
                .sorted(Comparator.comparing(PageComponentAssociate::getSorts))
                .collect(Collectors.toList());

        Document doc = null;
        List<ComponentImage> componentImages = new ArrayList<>();
        for (int i = 0; i < pageComponentAssociates.size(); i++) {
            ComponentImage componentImage = pageComponentAssociates.get(i).getComponentImage();
            if (StringUtils.isNotEmpty(componentImage.getComponentBodyApi())) {
                componentImages.add(componentImage);
            }
            //默认的父组件
            if (pageComponentAssociates.get(i).getLevel() == 1) {
                html.append(componentImage.getComponentBody());
                doc = Jsoup.parse(html.toString());
                continue;
            } else {
                String pId = pageComponentAssociates.get(i).getComponentImagePId().getId();
                String cId = pageComponentAssociates.stream().filter(pca -> pca.getComponentImage().getId().equals(pId)).collect(Collectors.toList()).get(0).getComponentImage().getComponentId();
                Elements element = doc.getElementsByAttributeValue("componentId", cId);
                String body = componentImage.getComponentBody();

                String str = randomStr();
                while (modelMap.containsKey(str)) {
                    str = randomStr();
                }
                str = "uXppmcPtoMxFeweSdZnJK";
                org.springframework.data.domain.Page o = (org.springframework.data.domain.Page) getData(componentImage.getComponentBodyApi(), new Pageable(filters(componentImage.getDataFilters()), 15));
                body = body.replaceAll("o\\.", str + ".");
                if (componentImage.getApiDataStructureType().equals(API_DATA_STRUCTURE_TYPES)) {
                    body = "<#list " + str + "List as " + str + " >" + body + "</#list>";
                }
                modelMap.put(str + "List", o.getContent());
                element.append(body);
                continue;
            }
        }
        doc = perfectHtml(doc);
        return new ComponentImageAndDocument(doc, modelMap, componentImages);
    }

}
