package com.miniits.base.controller.open;

import com.miniits.base.service.PageService;
import com.miniits.base.utils.ComponentImageAndDocument;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.miniits.base.utils.CommonUtil.mergePage;
import static com.miniits.base.utils.FileUtil.fileExists;
import static com.miniits.base.utils.HTMLUtil.createHtml;
import static com.miniits.base.utils.HTMLUtil.createTemplateFile;
import static com.miniits.base.utils.RequestUtil.getPath;
import static com.miniits.base.utils.SystemDict.GLOBAL_STATUS_NO;
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
        return "redirect:/index";
    }

    @GetMapping("/{path}")
    public String path(
            @PathVariable(value = "path") String path,
            ModelMap modelMap) {
        return "default/Article-Detail";
    }

    @GetMapping(value = {"index", "index.html"})
    public String index(ModelMap modelMap, HttpServletRequest httpServletRequest) throws IOException, TemplateException {
        if (fileExists(getPath("templates/customize/") + "/index.html")) {
            return "index";
        }
        ComponentImageAndDocument componentImageAndDocument = mergePage(modelMap, "index",httpServletRequest);
        createTemplateFile("ftl-index", componentImageAndDocument.getDocument().toString()
                .replaceAll("<!--#list-->", "</#list>")
                .replaceAll("&lt;", "<")
                .replaceAll("&gt;", ">"));
        modelMap = componentImageAndDocument.getModelMap();
        modelMap = renderingPage(modelMap);
        if (componentImageAndDocument.getPage().getCreateStaticFile().equals(GLOBAL_STATUS_NO)) {
            return modelMap.get("templateName").toString().split("\\.")[0];
        }
        createHtml(modelMap);
        return "index";
    }

    private ModelMap renderingPage(ModelMap modelMap) {
        isPackageExist(this.getClass().getResource("/templates/").getPath() + "customize/");
        String path = this.getClass().getResource("/templates/customize/").getPath();
        modelMap.put("path", path);
        modelMap.put("templateName", "ftl-index.ftl");
        modelMap.put("fileName", "index.html");
        return modelMap;
    }

}
