package com.miniits.base.controller.admin;

import com.miniits.base.model.entity.Image;
import com.miniits.base.mysql.Pageable;
import com.miniits.base.service.ImageServer;
import com.miniits.base.utils.BaseController;
import com.miniits.base.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.miniits.base.utils.Result.getTotalPage;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/4/20
 * @Time: 22:43
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
@Controller
@RequestMapping("/admin/images")
public class ImageController extends BaseController {

    private Long totalPage = 0L;

    @Autowired
    private ImageServer imageServer;

    @GetMapping("init")
    public String init(ModelMap modelMap, Pageable pageable) {
        Page<Image> images = imageServer.search(pageable);
        modelMap.put("active", "content");
        modelMap.put("thisPageNumber", pageable.getPageNumber());
        modelMap.put("pageNumbers", getPageNumber(images, pageable));
        modelMap.put("totalPageNumber", totalPage);
        modelMap.put("images", images.getContent());
        return "admin/views/content/Images";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Result getImage(@PathVariable(value = "id") String id) {
        return success(imageServer.findOne(id));
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Result deleteImage(@PathVariable(value = "id") String id) {
        imageServer.delete(id);
        return success("删除成功");
    }

    public List<Long> getPageNumber(Page<Image> page, Pageable pageable) {
        long thisPage = pageable.getPageNumber();
        totalPage = getTotalPage(page.getTotalElements(), pageable.getPageSize());

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
}
