package com.miniits.base.config;

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
