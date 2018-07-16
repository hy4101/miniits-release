package com.miniits.base.utils;

import com.miniits.base.model.dto.SeoDTO;
import com.miniits.base.model.entity.ComponentImage;
import com.miniits.base.model.entity.Page;
import com.miniits.base.model.entity.PageComponentAssociate;
import com.miniits.base.mysql.Pageable;
import com.miniits.base.service.ComponentImageServer;
import com.miniits.base.service.PageService;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

import static com.miniits.base.utils.DataUtil.getData;
import static com.miniits.base.utils.DataUtil.getPageData;
import static com.miniits.base.utils.FileUtil.isPackageExist;
import static com.miniits.base.utils.HTMLUtil.addHtmlDepend;
import static com.miniits.base.utils.HTMLUtil.freemarkerIsNull;
import static com.miniits.base.utils.RequestUtil.getRequest;
import static com.miniits.base.utils.Result.getTotalPage;
import static com.miniits.base.utils.SystemDict.*;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/4/6
 * @Time: 20:51
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
@Component
public class CommonUtil {

    private static Logger logger = LoggerFactory.getLogger(CommonUtil.class);
    @Autowired
    private ComponentImageServer componentImageServer;

    private static CommonUtil commonUtil;

    @PostConstruct
    private void init() {
        commonUtil = this;
        commonUtil.componentImageServer = this.componentImageServer;
    }

    private static Map<String, String> childElement = new HashMap<>();

    private static Long totalPage = 0L;

    private static final char[] A_z = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R'
            , 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm'
            , 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public static String randomStr() {
        Random rd = new Random();
        String str = "";
        for (int i = 0; i <= 20; i++) {
            str += A_z[rd.nextInt(52)];
        }
        return str;
    }

    /**
     * 获取该页面的所有组件
     * 组件在父组件内的排序
     * 组件合并页面
     * 数据渲染页面
     *
     * @return 组件列表和结构页面
     */
    public static ComponentImageAndDocument mergePage(ModelMap modelMap, String pageName, HttpServletRequest httpServletRequest) {
        StringBuffer html = new StringBuffer();
        String urlFilters = httpServletRequest.getParameter("filters");
        String requestURI = httpServletRequest.getRequestURI();
        Integer pageNumber = StringUtils.isEmpty(httpServletRequest.getParameter("pageNumber")) ? 1 : Integer.valueOf(httpServletRequest.getParameter("pageNumber"));
        Integer pageSize = StringUtils.isEmpty(httpServletRequest.getParameter("pageSize")) ? 1 : Integer.valueOf(httpServletRequest.getParameter("pageSize"));
        Page page = SpringContextHolder.getBean(PageService.class).getPage(pageName, 100000001);
        List<PageComponentAssociate> pageComponentAssociates = page.getPageComponentAssociates().stream()
                .filter(pca -> pca.getComponentImage().getComponentStatus().equals(100000001))
                .sorted(Comparator.comparing(PageComponentAssociate::getSorts))
                .collect(Collectors.toList());

        SeoDTO seoDTO = new SeoDTO(page.getId(), page.getKeywords(), page.getDescription(), page.getTitle());
        Document doc = null;
        List<ComponentImage> componentImages = new ArrayList<>();
        for (int i = 0; i < pageComponentAssociates.size(); i++) {
            ComponentImage componentImage = pageComponentAssociates.get(i).getComponentImage();
            String filters = !org.springframework.util.StringUtils.isEmpty(urlFilters) ? urlFilters : componentImage.getDataFilters();

            if (StringUtils.isNotEmpty(componentImage.getComponentBodyApi())) {
                componentImages.add(componentImage);
            }

            if (pageComponentAssociates.get(i).getLevel() == 1) {
                //组装默认的父组件
                html.append(componentImage.getComponentBody());
                doc = Jsoup.parse(html.toString());
                continue;
            } else {
                //组装父组件下的子组件
                String pId = pageComponentAssociates.get(i).getComponentImagePId().getId();
                String cId = pageComponentAssociates.stream().filter(pca -> pca.getComponentImage().getId().equals(pId)).collect(Collectors.toList()).get(0).getComponentImage().getComponentId();
                Elements element = doc.getElementsByAttributeValue("componentId", cId);
                String body = componentImage.getComponentBody();
                body = freemarkerIsNull(body);
                String str = randomStr();
                while (modelMap.containsKey(str)) {
                    str = randomStr();
                }
                if (page.getTemplateCaching().equals(GLOBAL_STATUS_YES)) {
                    str = componentImageKey(componentImage, str).getObjectKey();
                }
                /**
                 * 根据 API 获取数据
                 */
                if (StringUtils.isNotEmpty(componentImage.getComponentBodyApi()) && !org.springframework.util.StringUtils.isEmpty(filters)) {
                    Map<String, Object> map = getPageData(filters);

                    org.springframework.data.domain.Page o = (org.springframework.data.domain.Page) getData(componentImage.getComponentBodyApi(),
                            new Pageable(map.get("filters"), pageSize, !componentImage.getApiDataStructureType().equals(API_DATA_STRUCTURE_TYPE_PAGE) ? 1 : pageNumber));

                    body = body.replaceAll("object\\.", str + ".");
                    body = judgmentComponentType(body);
                    if (null != componentImage.getApiDataStructureType() && Arrays.asList(API_DATA_STRUCTURE_TYPE_PAGE, API_DATA_STRUCTURE_TYPE_NO_PAGE).contains(componentImage.getApiDataStructureType())) {
                        body = interactiveComponent(body, str, o, pageNumber, pageSize, requestURI);
                    }
                    if (!ObjectUtils.isEmpty(o)) {
                        modelMap.put(str + "List", o.getContent());
                    }
                }
                element.append(body);
                continue;
            }
        }
        if (!ObjectUtils.isEmpty(doc)) {
            doc = addHtmlDepend(doc, seoDTO);
        }
        return new ComponentImageAndDocument(doc, modelMap, componentImages, page);
    }

    private static ComponentImage componentImageKey(ComponentImage componentImage, String str) {
        return commonUtil.componentImageServer.getObjectKey(componentImage.getId(), str);
    }

    //组件交互
    private static String interactiveComponent(String body, String str, org.springframework.data.domain.Page page, Integer pageNumber, Integer pageSize, String requestURI) {
        String key = childElement.get("p-miniits-page-component");
        childElement.remove("p-miniits-page-component");
        if (!ObjectUtils.isEmpty(key)) {
            Document document = Jsoup.parse(key);
            Element li = document.select("li").get(0);
            document.select("li").remove();

            List<Long> ps = getPageNumber(page.getTotalElements(), new Pageable(pageSize, pageNumber));
            for (int i = 0; i < ps.size(); i++) {
                String active = "";
                if (Integer.valueOf(ps.get(i).toString()) == pageNumber) {
                    active = "active";
                }
                li.attr("class", active);
                li.select("a").attr("href", requestURI + "?pageNumber=" + ps.get(i) + "&pageSize=" + page.getSize()).html(ps.get(i).toString());
                document.select("ul").append(li.toString());
            }
            Document documentBody = Jsoup.parse(body);
            String baseList = documentBody.select(".p-miniits-component").get(0).children().toString();
            documentBody.select(".p-miniits-component").get(0).children().remove();

            String listBody = "<#list " + str + "List as " + str + " >" + baseList + "</#list>";
            documentBody.select(".p-miniits-component").append(listBody);
            documentBody.select(".p-miniits-component").append(document.select("nav.miniits-page-component").toString());
            body = documentBody.toString();
        } else {
            body = "<#list " + str + "List as " + str + " >" + body + "</#list>";
        }
        return body;
    }

    public static String judgmentComponentType(String ele) {
        Document document = Jsoup.parse(ele);
        //处理分页组件
        if (!document.select("nav.miniits-page-component").toString().isEmpty()) {
            return pageComponent(document);
        }
        return ele;
    }

    private static String pageComponent(Document document) {
        childElement.put("p-miniits-page-component", document.select("nav.miniits-page-component").toString());
        document.select("nav.miniits-page-component").parents().get(0).addClass("p-miniits-component");
        document.select("nav.miniits-page-component").remove();
        return document.toString();
    }

    public static List<Long> getPageNumber(long totalElements, Pageable pageable) {
        long thisPage = pageable.getPageNumber();
        totalPage = getTotalPage(totalElements, pageable.getPageSize());

        if (totalPage < 5) {
            List<Long> ls = new ArrayList<>();
            for (long i = 0; i < totalPage; i++) {
                ls.add(++i);
                --i;
            }
            return ls;
        }

        if (thisPage <= 3 && totalPage >= 5) {
            return Arrays.asList(1L, 2L, 3L, 4L, 5L);
        }
        if (thisPage > totalPage - 3) {
            return Arrays.asList(totalPage - 4, totalPage - 3, totalPage - 2, totalPage - 1, totalPage);
        }
        return Arrays.asList(thisPage - 2, thisPage - 1, thisPage, thisPage + 1, thisPage + 2);
    }

    public static ModelMap renderingPage(ModelMap modelMap, String pageName, String fileName, HttpServletRequest httpServletRequest) {
        String base = "/templates/customize/" + pageName + "/";
        logger.info(getRequest().getServletContext().getClassLoader().getClass().toString());
//        String path = getRequest().getServletContext().getContextPath() + base;
        String path = commonUtil.getClass().getClassLoader().getResource("").getPath() + base;
        isPackageExist(path);
        modelMap.put("path", path);
        modelMap.put("templateName", "ftl-" + pageName + ".ftl");
        modelMap.put("fileName", pageName + "_" + fileName + ".html");
        return modelMap;
    }

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        InputStream ra = CommonUtil.class.getResourceAsStream("banner.txt");
        properties.load(ra);
        String path = properties.getProperty("path");
    }


}
