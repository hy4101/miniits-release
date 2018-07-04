package com.miniits.base.config;

import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author: wq
 * @Date: 2018/7/4
 * @Time: 17:47
 * <p>
 * Description:
 * ***
 */
@Order(1)
@WebFilter(filterName = "MPlusFilter", urlPatterns = "/*")
public class MPlusFilter implements Filter {

    private static final String LINUX_TEMPLATE_PATH = "/home/user/m-plus/template/";

    private static final String WINDOWS_TEMPLATE_PATH = "c:\\user\\m-plus\\template\\";

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Resource
    private Configuration configuration;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//
//        FileTemplateLoader ftl1 = new FileTemplateLoader(new File("/tmp/templates"));
//        FileTemplateLoader ftl2 = new FileTemplateLoader(new File("/usr/data/templates"));
//        ClassTemplateLoader ctl = new ClassTemplateLoader(getClass(), "");
//        TemplateLoader[] loaders = new TemplateLoader[] { ftl1, ftl2, ctl };
//        MultiTemplateLoader mtl = new MultiTemplateLoader(loaders);
//        configuration.setTemplateLoader(mtl);
//
//        if (httpServletRequest.getRequestURI().indexOf("admin") > -1 || httpServletRequest.getRequestURI().indexOf("static") > -1) {
////            freeMarkerConfigurer.setTemplateLoaderPath("classpath:/templates");
//            configuration.setServletContextForTemplateLoading(request.getServletContext(), "/templates");
//        } else {
//            String tp = createTemplateFolderAndHtmlFolder(LINUX_TEMPLATE_PATH, WINDOWS_TEMPLATE_PATH);
//            configuration.setDirectoryForTemplateLoading(new File(tp));
//        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
