package com.miniits.base.controller.gather.service;

import com.miniits.base.controller.gather.pipeline.ImageConsolePipeline;
import com.miniits.base.model.entity.Image;
import com.miniits.base.utils.BaseController;
import com.miniits.base.utils.HTMLSpirit;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;


/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/1/24
 * @Time: 21:58
 * <p>
 * Description:
 * ***
 */
@Service
public class ImageCrawlerService extends BaseController implements PageProcessor {
    private Site site = Site.me().setCharset("UTF-8").setRetryTimes(3).setSleepTime(1000);

    private int number = 0;

    @Autowired
    private ImageConsolePipeline consolePipeline;

    /**
     * 图片上传
     */
    public void imagesUpload(List<String> fileUrls) {
        Spider.create(new ImageCrawlerService()).
                addUrl(fileUrls.toArray(new String[fileUrls.size()])).
                addPipeline(consolePipeline).thread(1).run();
    }

    @Override
    public void process(Page page) {
        Selectable content = page.getHtml().xpath("//div[@id=tab-codes]/div/div[@class=panel-share-item]");
        if (content.get().length() <= 0) {
            try {
                throw new Exception("url错误");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        String addressUrl = page.getUrl().get();
        String name = page.getHtml().xpath("//div/h1[@class=viewer-title]/text()").get();
        String url = content.xpath("//div/input[@id=embed-code-2]").get();
        url = HTMLSpirit.match(url, "input", "value").get(0);
        String HTML = content.xpath("//div/input[@id=embed-code-3]").get();
        HTML = Jsoup.parse(HTML).select("input").attr("value");
        String BBCode = content.xpath("//div/input[@id=embed-code-4]").get();
        BBCode = HTMLSpirit.match(BBCode, "input", "value").get(0);
        String Markdown = content.xpath("//div/input[@id=embed-code-5]").get();
        Markdown = HTMLSpirit.match(Markdown, "input", "value").get(0);

        Image image = new Image();
        image.setAddressUrl(addressUrl);
        image.setName(name);
        image.setUrl(url);
        image.setHtml(HTML);
        image.setBBCode(BBCode);
        image.setMarkdown(Markdown);
        if (StringUtils.isEmpty(image.getUrl())) {
            page.setSkip(true);
        } else {
            page.putField("bean", image);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

}
