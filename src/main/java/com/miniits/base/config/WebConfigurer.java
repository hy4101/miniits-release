package com.miniits.base.config;

import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.TemplateException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/3/17
 * @Time: 14:35
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
@Configuration
@EnableWebMvc
public class WebConfigurer extends WebMvcConfigurerAdapter {

//    @Bean
//    public FreeMarkerConfigExtend freeMarkerConfigExtend(FreeMarkerConfigExtend freeMarkerConfigExtend) {
//        freeMarkerConfigExtend.setTemplateLoaderPath("/templates");
//        freeMarkerConfigExtend.setDefaultEncoding("UTF-8");
//        Properties properties = new Properties();
//        properties.setProperty("template_update_delay", "0");
//        properties.setProperty("locale", "zh_CN");
//        properties.setProperty("default_encoding", "UTF");
//        freeMarkerConfigExtend.setFreemarkerSettings(properties);
//        return freeMarkerConfigExtend;
//    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() throws IOException, TemplateException {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("classpath:/templates");
        freemarker.template.Configuration configuration = freeMarkerConfigurer.createConfiguration();
        configuration.setDefaultEncoding("UTF-8");
        //这里可以添加其他共享变量 比如sso登录地址
        configuration.setSharedVariable("shiro", new ShiroTags());
        freeMarkerConfigurer.setConfiguration(configuration);
        return freeMarkerConfigurer;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**", "/templates/customize/**").addResourceLocations("classpath:/static/", "classpath:/templates/customize/");
        super.addResourceHandlers(registry);
    }

    @Bean
    @Order(1)
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/templates/customize/");
        resolver.setSuffix(".html");
        InternalResourceView d = new InternalResourceView();
        resolver.setViewClass(d.getClass());
        resolver.setContentType("text/html;charset=UTF-8");
        return resolver;
    }

    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}
