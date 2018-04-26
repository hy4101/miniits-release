package com.miniits.base.controller.gather.pipeline;

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
public class ConsolePipeline implements Pipeline {

//    @Autowired
//    private MoviesService moviesService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        System.out.println("get page: " + resultItems.getRequest().getUrl());
        Iterator var3 = resultItems.getAll().entrySet().iterator();

        while (var3.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry) var3.next();
//            Movies movies = (Movies) entry.getValue();
//            moviesService.save(movies);
        }
    }
}
