package com.miniits.base.controller.open;

import com.miniits.base.utils.ComponentImageAndDocument;
import freemarker.template.TemplateException;
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
 * @Date: 2018/4/12
 * @Time: 23:12
 * <p>
 * Description:
 * WWW.MINIITS.COM
 * 其余自定义页面的访问路径
 */
@Controller
@RequestMapping("${domain.path}")
public class OrtherPageController {

    @GetMapping
    public String index() {
        return "redirect:/index";
    }

    @GetMapping("{page-name}")
    public String test(ModelMap modelMap, @PathVariable(value = "page-name") String pageName, HttpServletRequest httpServletRequest) throws IOException, TemplateException {
        if (fileExists(getPath("templates/customize/" + pageName + "/") + "/" + pageName + ".html")) {
            return pageName + "/" + pageName;
        }
        ComponentImageAndDocument componentImageAndDocument = mergePage(modelMap, pageName,httpServletRequest);
        createTemplateFile("ftl-" + pageName, componentImageAndDocument.getDocument().toString()
                .replaceAll("<!--#list-->", "</#list>")
                .replaceAll("&lt;", "<")
                .replaceAll("&gt;", ">"));
        modelMap = componentImageAndDocument.getModelMap();
        modelMap = renderingPage(modelMap, pageName);
        if (componentImageAndDocument.getPage().getCreateStaticFile().equals(GLOBAL_STATUS_NO)) {
            return modelMap.get("templateName").toString().split("\\.")[0];
        }
        createHtml(modelMap);
        return pageName + "/" + pageName;
    }

    private ModelMap renderingPage(ModelMap modelMap, String pageName) {
        isPackageExist(this.getClass().getResource("/templates/").getPath() + "customize/" + pageName + "/");
        String path = this.getClass().getResource("/templates/customize/" + pageName + "/").getPath();
        modelMap.put("path", path);
        modelMap.put("templateName", "ftl-" + pageName + ".ftl");
        modelMap.put("fileName", pageName + ".html");
        return modelMap;
    }
}
