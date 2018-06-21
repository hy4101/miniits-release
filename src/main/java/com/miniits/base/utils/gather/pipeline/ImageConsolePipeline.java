package com.miniits.base.utils.gather.pipeline;

import com.miniits.base.model.entity.Image;
import com.miniits.base.service.ImageServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Iterator;
import java.util.Map;

/**
 * @author: wq
 * @Date: 2018/1/23
 * @Time: 14:13
 * <p>
 * Description:
 * ***
 */
@Service
public class ImageConsolePipeline implements Pipeline {


    @Autowired
    private ImageServer imageServer;

    @Override
    public void process(ResultItems resultItems, Task task) {
        System.out.println("get page: " + resultItems.getRequest().getUrl());
        Iterator var3 = resultItems.getAll().entrySet().iterator();

        while (var3.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry) var3.next();
            Image movies = (Image) entry.getValue();
            imageServer.save(movies);
        }
    }
}
