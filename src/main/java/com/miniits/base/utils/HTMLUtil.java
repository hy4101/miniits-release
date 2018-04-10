package com.miniits.base.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Map;

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

    public static String createHtml(Map<String, Object> root) {
        String path = root.get("path").toString();
        try {
            Template temp = htmlUtil.configuration.getTemplate(root.get("templateName").toString());
            isPackageExist(path);
            path = path + root.get("fileName").toString();
            Writer writer = new FileWriter(new File(path.substring(path.indexOf("/"))));
            temp.process(root, writer);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        deleteFile(new File(getPath("templates/") + root.get("fileName").toString().replaceAll(".html", ".ftl")));
        return path;
    }

    public static void createTemplateFile(String fileName, String fileContent) {
        byte[] sourceByte = fileContent.getBytes();
        if (null != sourceByte) {
            try {
                File file = new File(getPath("templates/") + "/" + fileName + ".ftl");
                deleteFile(file);
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

    public static boolean deleteFile(File file) {
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static Document perfectHtml(Document doc) {
        doc.getElementsByTag("head").append("<meta charset=\"UTF-8\">");
        doc.getElementsByTag("head").append("<title>Title</title>");
        doc.getElementsByTag("head").append("<script src=\"https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js\"></script>");
        doc.getElementsByTag("head").append("<link rel=\"stylesheet\" href=\"https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css\" >");
        doc.getElementsByTag("head").append("<script src=\"https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>");
        doc.getElementsByTag("head").append("<link href=\"https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css\" rel=\"stylesheet\">");
        return doc;
    }

}
