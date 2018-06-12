package com.miniits.base.controller.open;

import com.miniits.base.utils.ComponentImageAndDocument;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.miniits.base.utils.CommonUtil.mergePage;
import static com.miniits.base.utils.CommonUtil.renderingPage;
import static com.miniits.base.utils.FileUtil.fileExists;
import static com.miniits.base.utils.FileUtil.validateFileName;
import static com.miniits.base.utils.HTMLUtil.*;
import static com.miniits.base.utils.RequestUtil.getFilters;
import static com.miniits.base.utils.RequestUtil.getPath;
import static com.miniits.base.utils.SystemDict.GLOBAL_STATUS_NO;

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

    /**
     * 访问 首页 index 接口
     * 设置了页面静态化，则优先访问静态文件
     * 否则页面动态显示
     *
     * @param modelMap
     * @param httpServletRequest
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    @GetMapping(value = {"/", "index", "index.html"})
    public String index(ModelMap modelMap, HttpServletRequest httpServletRequest) throws IOException, TemplateException {
        String filters = validateFileName("index_" + getFilters(httpServletRequest));
        Integer pageNumber = StringUtils.isEmpty(httpServletRequest.getParameter("pageNumber")) ? 1 : Integer.valueOf(httpServletRequest.getParameter("pageNumber"));
        Integer pageSize = StringUtils.isEmpty(httpServletRequest.getParameter("pageSize")) ? 1 : Integer.valueOf(httpServletRequest.getParameter("pageSize"));

        if (fileExists(getPath("templates/customize/index/") + "/index_" + pageNumber + ".html")) {
            return "index/index_" + pageNumber;
        }
        ComponentImageAndDocument componentImageAndDocument = mergePage(modelMap, "index", httpServletRequest);
        if (componentImageAndDocument.getPage().getTemplateCaching().equals(100000002)) {
            createTemplateFile("ftl-index", convertFreemarkerFormat(componentImageAndDocument.getDocument().toString()));
        }
        modelMap = componentImageAndDocument.getModelMap();
        modelMap = renderingPage(modelMap, "index", pageNumber.toString(), httpServletRequest);
        if (componentImageAndDocument.getPage().getCreateStaticFile().equals(GLOBAL_STATUS_NO)) {
            return modelMap.get("templateName").toString().split("\\.")[0];
        }
        createHtml(modelMap);
        return "index/" + (modelMap.get("fileName").toString().replaceAll(".html", ""));
    }

}
