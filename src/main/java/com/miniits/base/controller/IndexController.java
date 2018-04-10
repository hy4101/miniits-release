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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.miniits.base.utils.HTMLUtil.createHtml;
import static com.miniits.base.utils.HTMLUtil.createTemplateFile;
import static com.miniits.base.utils.HTMLUtil.perfectHtml;
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
//        return "default/Index";
    }

    @GetMapping("/{path}")
    public String path(
            @PathVariable(value = "path") String path,
            ModelMap modelMap) {
        return "default/Article-Detail";
    }

    @GetMapping(value = {"index", "index.html"})
    public String index() {
        Document doc = mergePage();
        createTemplateFile("ftl-index", doc.toString());
        Map<String, Object> map = renderingPage();
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
        map.put("test", "士大夫士大水水水水水水水水水水水");
        return map;
    }

    private Document mergePage() {
        StringBuffer html = new StringBuffer();
        Page page = pageService.getPage("index", 100000001);
        List<PageComponentAssociate> pageComponentAssociates = page.getPageComponentAssociates().stream().filter(pca -> pca.getComponentImage().getComponentStatus().equals(100000001)).sorted(Comparator.comparing(PageComponentAssociate::getSorts)).collect(Collectors.toList());
        Document doc = null;
        for (int i = 0; i < pageComponentAssociates.size(); i++) {
            ComponentImage componentImage = pageComponentAssociates.get(i).getComponentImage();
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
        doc = perfectHtml(doc);
        return doc;
    }

//    private Document perfectHtml(Document doc) {
//        doc.getElementsByTag("head").append("<meta charset=\"UTF-8\">");
//        doc.getElementsByTag("head").append("<title>Title</title>");
//        doc.getElementsByTag("head").append("<script src=\"https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js\"></script>");
//        doc.getElementsByTag("head").append("<link rel=\"stylesheet\" href=\"https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css\" >");
//        doc.getElementsByTag("head").append("<script src=\"https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>");
//        doc.getElementsByTag("head").append("<link href=\"https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css\" rel=\"stylesheet\">");
//        return doc;
//    }
}
