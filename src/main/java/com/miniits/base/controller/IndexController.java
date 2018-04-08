package com.miniits.base.controller;

import com.miniits.base.model.entity.ComponentImage;
import com.miniits.base.model.entity.Page;
import com.miniits.base.model.entity.PageComponentAssociate;
import com.miniits.base.service.PageService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.miniits.base.utils.HTMLUtil.createTemplateFile;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/3/16
 * @Time: 22:13
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
@Controller
@RequestMapping("/fc")
public class IndexController {

    @Autowired
    private PageService pageService;

    @GetMapping
    public String test(ModelMap modelMap) {
        modelMap.put("title", "the is title");
        modelMap.put("content", "HotArticles");
        modelMap.put("description", "the is description");
        modelMap.put("keywords", "the is keywords");
        return "default/Index";
    }

    @GetMapping("/{path}")
    public String path(
            @PathVariable(value = "path") String path,
            ModelMap modelMap) {
        return "default/Article-Detail";
    }

    @GetMapping("index")
    public String index() {
        StringBuffer html = new StringBuffer();
        Page page = pageService.getPage("index", 100000001);
        List<PageComponentAssociate> pageComponentAssociates = page.getPageComponentAssociates().stream().sorted(Comparator.comparing(PageComponentAssociate::getSorts)).collect(Collectors.toList());
//        int level = 1;
        Document doc = null;
        for (int i = 0; i < pageComponentAssociates.size(); i++) {
            ComponentImage componentImage = pageComponentAssociates.get(i).getComponentImage();
//            if (level != pageComponentAssociates.get(i).getLevel()) {
//                ++level;
//            }
            if (pageComponentAssociates.get(i).getLevel() == 1) {
                html.append(componentImage.getComponentBody());
                doc = Jsoup.parse(html.toString());
                continue;
            } else {
                String pId = pageComponentAssociates.get(i).getComponentImagePId().getId();
                String cId = pageComponentAssociates.stream().filter(pca -> pca.getComponentImage().getId().equals(pId)).collect(Collectors.toList()).get(0).getComponentImage().getComponentId();
                Elements element = doc.getElementsByAttributeValue("componentId", cId);
                element.append(componentImage.getComponentBody());
                continue;
            }
        }
        createTemplateFile("index",doc.toString());
        return "fd";
    }
}
