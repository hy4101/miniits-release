package com.miniits.base.controller.open;

import com.miniits.base.utils.ComponentImageAndDocument;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.miniits.base.utils.CommonUtil.mergePage;
import static com.miniits.base.utils.FileUtil.fileExists;
import static com.miniits.base.utils.HTMLUtil.convertFreemarkerFormat;
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
@RequestMapping("/")
public class IndexController {

    private final static Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @GetMapping(value = {"/", "index", "index.html"})
    public String index(ModelMap modelMap, HttpServletRequest httpServletRequest) throws IOException, TemplateException {
        if (fileExists(getPath("templates/customize/") + "/index.html")) {
            return "index";
        }
        ComponentImageAndDocument componentImageAndDocument = mergePage(modelMap, "index", httpServletRequest);
        createTemplateFile("ftl-index", convertFreemarkerFormat(componentImageAndDocument.getDocument().toString()));
        modelMap = componentImageAndDocument.getModelMap();
        modelMap = renderingPage(modelMap);
        if (componentImageAndDocument.getPage().getCreateStaticFile().equals(GLOBAL_STATUS_NO)) {
            return modelMap.get("templateName").toString().split("\\.")[0];
        }
        createHtml(modelMap);
        return "index";
    }

    private ModelMap renderingPage(ModelMap modelMap) {
        Class c = this.getClass();
//        file:/usr/local/java/m-plus.jar!/BOOT-INF/classes!/templates/
        LOGGER.info("111111111111111111111111111111111111111");
        LOGGER.info(c.toString());

        isPackageExist(c.getResource("/templates/").getPath() + "customize/");
        String path = c.getResource("/templates/customize/").getPath();
        modelMap.put("path", path);
        modelMap.put("templateName", "ftl-index.ftl");
        modelMap.put("fileName", "index.html");
        return modelMap;
    }

}
