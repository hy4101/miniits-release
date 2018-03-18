package com.miniits.base.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Map;

import static com.miniits.base.utils.SystemFile.isPackageExist;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/3/3
 * @Time: 12:52
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
@Component
public class HTMLUtil {

    @Resource
    private Configuration configuration;

    private static HTMLUtil htmlUtil;

    @PostConstruct
    public void init() {
        htmlUtil = this;
        htmlUtil.configuration = this.configuration;
    }

    public static String createHtml(Map<String, Object> root) {
        String path = root.get("path").toString();
        try {
            Template temp = htmlUtil.configuration.getTemplate(root.get("templateName").toString());
            isPackageExist(path);
            path = path + root.get("fileName").toString();
            Writer file = new FileWriter(new File(path.substring(path.indexOf("/"))));
            temp.process(root, file);
            file.flush();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }
}
