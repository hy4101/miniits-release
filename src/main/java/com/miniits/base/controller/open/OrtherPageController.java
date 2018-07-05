package com.miniits.base.controller.open;

import com.miniits.base.utils.ComponentImageAndDocument;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

import static com.miniits.base.utils.CommonUtil.mergePage;
import static com.miniits.base.utils.CommonUtil.renderingPage;
import static com.miniits.base.utils.FileUtil.*;
import static com.miniits.base.utils.HTMLUtil.convertFreemarkerFormat;
import static com.miniits.base.utils.HTMLUtil.createHtml;
import static com.miniits.base.utils.RequestUtil.getFilters;
import static com.miniits.base.utils.RequestUtil.getPath;
import static com.miniits.base.utils.SystemDict.GLOBAL_STATUS_NO;

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

    private static Logger logger = LoggerFactory.getLogger(OrtherPageController.class);

    @GetMapping
    public String index() {
        return "redirect:/index";
    }

    @GetMapping("{page-name}")
    public String entrance(ModelMap modelMap, @PathVariable(value = "page-name") String pageName, HttpServletRequest httpServletRequest) throws IOException, TemplateException {

        String filters = validateFileName(pageName + "_" + getFilters(httpServletRequest));
        Integer pageNumber = StringUtils.isEmpty(httpServletRequest.getParameter("pageNumber")) ? 1 : Integer.valueOf(httpServletRequest.getParameter("pageNumber"));

        if (fileExists(getPath("templates/customize/" + pageName + "/") + pageName + "_" + pageNumber + "_" + filters + ".html")) {
            logger.warn("start html path:{}", pageName + "/" + pageName + "_" + pageNumber + "_" + filters);
            return pageName + "/" + pageName + "_" + pageNumber + "_" + filters;
        }
        ComponentImageAndDocument componentImageAndDocument = mergePage(modelMap, pageName, httpServletRequest);
        modelMap = componentImageAndDocument.getModelMap();
        modelMap = renderingPage(modelMap, pageName, pageNumber + "_" + filters, httpServletRequest);
        String path = modelMap.get("templateName").toString().split("\\.")[0];
        /**
         * 创建模板
         */
        if (!fileExists(path) && componentImageAndDocument.getPage().getTemplateCaching().equals(GLOBAL_STATUS_NO)) {
            logger.warn("创建模板----1");
            createTemplateFile("ftl-" + pageName, convertFreemarkerFormat(componentImageAndDocument.getDocument().toString()));
        }
        if (componentImageAndDocument.getPage().getCreateStaticFile().equals(GLOBAL_STATUS_NO)) {
            logger.warn("判断模板是否存在");
            File file = new File(getPath("templates/") + "/" + "ftl-" + pageName + ".ftl");
            if (!file.exists()) {
                logger.warn("模板不存在，创建模板----2");
                createTemplateFile("ftl-" + pageName, convertFreemarkerFormat(componentImageAndDocument.getDocument().toString()));
            }
            logger.warn("start cache path:{}", path);
            return path;
        }
        logger.warn("start create html");
        createHtml(modelMap);
        logger.warn("start create success");
//        return pageName + "/" + (modelMap.get("fileName").toString().replaceAll(".html", ""));
        return getPath() + "customize\\tes\\"+(modelMap.get("fileName").toString());
    }

}
