package com.miniits.base.controller.gather.service;

import com.miniits.base.controller.gather.pipeline.ConsolePipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;


/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/1/24
 * @Time: 21:58
 * <p>
 * Description:
 * ***
 */
@Controller
@RequestMapping("/admin/images/")
public class ImageCrawlerService implements PageProcessor {
    private Site site = Site.me().setCharset("UTF-8").setRetryTimes(3).setSleepTime(1000);

    private int number = 0;

    @Autowired
    private ConsolePipeline consolePipeline;

    /**
     * 高清网（http://gaoqing.la）
     */
    @GetMapping("/upload/{fileUrl}")
    public void upload(@PathVariable(value = "fileUrl") String fileUrl) {
        Spider.create(new ImageCrawlerService()).
                addUrl("http://www.btbtdy.com/btdy/dy11953.html").
                addPipeline(consolePipeline).thread(1).run();
    }

    @Override
    public void process(Page page) {
        Selectable content = page.getHtml().xpath("//div[@id=body]/div/table");
    }

    @Override
    public Site getSite() {
        return site;
    }

}
