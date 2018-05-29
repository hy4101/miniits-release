package com.miniits.base.utils;

import com.miniits.base.model.dto.SeoDTO;
import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.util.options.MutableDataSet;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     * 将 markdown 文本内容转成 html 格式
     *
     * @param markdown
     * @return
     */
    public static String markdownToHtml(String markdown) {
        MutableDataSet options = new MutableDataSet();
        options.setFrom(ParserEmulationProfile.MARKDOWN);
        options.set(Parser.EXTENSIONS, Arrays.asList(TablesExtension.create()));

        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();
        Node document = parser.parse(markdown);
        renderer.render(document);
        return renderer.render(document);
    }

    /**
     * 将 html 转化成 freemarker 格式
     *
     * @param document
     * @return
     */
    public static String convertFreemarkerFormat(String document) {
        document = document
                .replaceAll("<!--#list-->", "</#list>")
                .replaceAll("<!--#if-->", "</#if>")
                .replaceAll("&lt;", "<")
                .replaceAll("&gt;", ">");
        return document;
    }


    public static String freemarkerIsNull(String content) {
        String regex = "\\$\\{object\\.[a-zA-Z]*\\}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(content);
        while (m.find()) {
            String par = m.group();
            par = escapeExprSpecialWord(par);
            String condition = par.substring(4, par.length() - 1);
            content = content.replaceAll(par, "<#if (" + condition + ")??>" + par + "</#if>");
        }
        System.out.println(content);
        return content;
    }

    public static String escapeExprSpecialWord(String keyword) {
        if (StringUtils.isNotBlank(keyword)) {
            String[] fbsArr = {"$", "{", "}"};
            for (String key : fbsArr) {
                if (keyword.contains(key)) {
                    keyword = keyword.replace(key, "\\" + key);
                }
            }
        }
        return keyword;
    }

    /**
     * 创建模板文件
     *
     * @param fileName
     * @param fileContent
     */
    public static void createTemplateFile(String fileName, String fileContent) {
        byte[] sourceByte = ("<@compress single_line=true>" + fileContent + "</@compress>").getBytes();
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
    public static Document addHtmlDepend(Document doc, SeoDTO seo) {
        doc.getElementsByTag("head").append("<meta charset=\"UTF-8\">");
        doc.getElementsByTag("head").append("<title>" + seo.getTitle() + "</title>");
        doc.getElementsByTag("head").append("<meta name=\"keywords\" content=\"" + seo.getKeywords() + "\">");
        doc.getElementsByTag("head").append("<meta name=\"description\" content=\"" + seo.getDescription() + "\">");
        doc.getElementsByTag("head").append("<script src=\"/static/js/jQuery-3.3.1.min.js\"></script>");
        doc.getElementsByTag("head").append("<link rel=\"stylesheet\" href=\"/static/bootstrap-3.3.7-dist/css/bootstrap.css\" >");
        doc.getElementsByTag("head").append("<script src=\"/static/bootstrap-3.3.7-dist/js/bootstrap.js\"></script>");
        doc.getElementsByTag("head").append("<link href=\"/static/css/font-awesome/font-awesome.min.css\" rel=\"stylesheet\">");
        doc.getElementsByTag("head").append("<link href=\"/static/css/Miniits_Common.css\" rel=\"stylesheet\">");
        return doc;
    }

}
