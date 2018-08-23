package com.miniits.base.controller;


import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 *
 */
@Controller
public class FreemarkerController implements TemplateMethodModelEx {

    public FreemarkerController() {
    }

    @Override
    public Object exec(List list) throws TemplateModelException {
        return "1w";
    }

}

