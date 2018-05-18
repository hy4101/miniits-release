package com.miniits.base.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.*;
import java.util.Map;

import static com.miniits.base.utils.FileUtil.deleteFile;
import static com.miniits.base.utils.RequestUtil.getPath;
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

    /**
     * 生成 HTML 静态文件
     *
     * @param root
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    public static String createHtml(Map<String, Object> root) throws IOException, TemplateException {
        String path = root.get("path").toString();
        Template temp = htmlUtil.configuration.getTemplate(root.get("templateName").toString());
        isPackageExist(path);
        path = path + root.get("fileName").toString();
        Writer writer = new FileWriter(new File(path.substring(path.indexOf("/"))));
        try {
            temp.process(root, writer);
        } catch (Exception e) {

        }
        writer.flush();
        writer.close();
        return path;
    }

    /**
     * 创建模板文件
     *
     * @param fileName
     * @param fileContent
     */
    public static void createTemplateFile(String fileName, String fileContent) {
        byte[] sourceByte = fileContent.getBytes();
        if (null != sourceByte) {
            try {
                String path = getPath("templates/") + "/" + fileName + ".ftl";
                File file = new File(path);
                deleteFile(path);
                if (!file.exists()) {
                    File dir = new File(file.getParent());
                    dir.mkdirs();
                    file.createNewFile();
                }
                FileOutputStream outStream = new FileOutputStream(file);
                outStream.write(sourceByte);
                outStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 添加 HTML 依赖
     * 添加 SEO  属性
     *
     * @param doc
     * @return
     */
    public static Document addHtmlDepend(Document doc, SEO seo) {
        doc.getElementsByTag("head").append("<meta charset=\"UTF-8\">");
        doc.getElementsByTag("head").append("<title>" + seo.getTitle() + "</title>");
        doc.getElementsByTag("head").append("<meta name=\"keywords\" content=\"" + seo.getKeys() + "\">");
        doc.getElementsByTag("head").append("<meta name=\"description\" content=\"" + seo.getDescription() + "\">");
        doc.getElementsByTag("head").append("<script src=\"/static/js/jQuery-3.3.1.min.js\"></script>");
        doc.getElementsByTag("head").append("<link rel=\"stylesheet\" href=\"/static/bootstrap-3.3.7-dist/css/bootstrap.css\" >");
        doc.getElementsByTag("head").append("<script src=\"/static/bootstrap-3.3.7-dist/js/bootstrap.js\"></script>");
        doc.getElementsByTag("head").append("<link href=\"/static/css/font-awesome/font-awesome.min.css\" rel=\"stylesheet\">");
        doc.getElementsByTag("head").append("<link href=\"/static/css/Miniits_Common.css\" rel=\"stylesheet\">");
        return doc;
    }

}
