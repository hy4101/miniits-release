package com.miniits.base.utils;

import com.miniits.base.model.entity.ComponentImage;
import com.miniits.base.model.entity.Page;
import com.miniits.base.model.entity.PageComponentAssociate;
import com.miniits.base.mysql.Pageable;
import com.miniits.base.service.PageService;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.miniits.base.utils.DataUtil.filters;
import static com.miniits.base.utils.DataUtil.getData;
import static com.miniits.base.utils.HTMLUtil.addHtmlDepend;
import static com.miniits.base.utils.SystemDict.API_DATA_STRUCTURE_TYPES;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/4/6
 * @Time: 20:51
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
public class CommonUtil {

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
     *
     * @return 组件列表和结构页面
     */
    public static ComponentImageAndDocument mergePage(ModelMap modelMap, String pageName) {
        StringBuffer html = new StringBuffer();
        Page page = SpringContextHolder.getBean(PageService.class).getPage(pageName, 100000001);
        List<PageComponentAssociate> pageComponentAssociates = page.getPageComponentAssociates().stream()
                .filter(pca -> pca.getComponentImage().getComponentStatus().equals(100000001))
                .sorted(Comparator.comparing(PageComponentAssociate::getSorts))
                .collect(Collectors.toList());

        Document doc = null;
        List<ComponentImage> componentImages = new ArrayList<>();
        for (int i = 0; i < pageComponentAssociates.size(); i++) {
            ComponentImage componentImage = pageComponentAssociates.get(i).getComponentImage();
            if (StringUtils.isNotEmpty(componentImage.getComponentBodyApi())) {
                componentImages.add(componentImage);
            }
            //默认的父组件
            if (pageComponentAssociates.get(i).getLevel() == 1) {
                html.append(componentImage.getComponentBody());
                doc = Jsoup.parse(html.toString());
                continue;
            } else {
                String pId = pageComponentAssociates.get(i).getComponentImagePId().getId();
                String cId = pageComponentAssociates.stream().filter(pca -> pca.getComponentImage().getId().equals(pId)).collect(Collectors.toList()).get(0).getComponentImage().getComponentId();
                Elements element = doc.getElementsByAttributeValue("componentId", cId);
                String body = componentImage.getComponentBody();

                String str = randomStr();
                while (modelMap.containsKey(str)) {
                    str = randomStr();
                }
                org.springframework.data.domain.Page o = (org.springframework.data.domain.Page) getData(componentImage.getComponentBodyApi(), new Pageable(filters(componentImage.getDataFilters()), 15));
                body = body.replaceAll("object\\.", str + ".");
                if (null != componentImage.getApiDataStructureType() && componentImage.getApiDataStructureType().equals(API_DATA_STRUCTURE_TYPES)) {
                    body = "<#list " + str + "List as " + str + " >" + body + "</#list>";
                }
                if (!ObjectUtils.isEmpty(o)) {
                    modelMap.put(str + "List", o.getContent());
                }
                element.append(body);
                continue;
            }
        }
        doc = addHtmlDepend(doc, new SEO());
        return new ComponentImageAndDocument(doc, modelMap, componentImages, page);
    }

}
