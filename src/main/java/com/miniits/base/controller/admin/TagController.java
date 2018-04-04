package com.miniits.base.controller.admin;

import com.miniits.base.model.entity.Tag;
import com.miniits.base.mysql.Pageable;
import com.miniits.base.service.TagServer;
import com.miniits.base.utils.BaseController;
import com.miniits.base.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author: wq
 * @Date: 2018/4/3
 * @Time: 17:57
 * <p>
 * Description:
 * ***
 */
@Controller
@RequestMapping("/admin/tags")
public class TagController extends BaseController {

    @Autowired
    private TagServer tagServer;

    @GetMapping("init")
    public String init(ModelMap modelMap) {
        modelMap.put("active", "content");
        return "admin/views/content/tags";
    }

    @GetMapping
    @ResponseBody
    public Result pages(Pageable pageable) {
        Page<Tag> tags = tagServer.search(pageable);
        return page(tags.getContent()).page(pageable.getPageSize()).size(pageable.getPageNumber()).
                totalCount(tags.getTotalElements()).total(tags.getTotalElements());
    }

    @PostMapping
    @ResponseBody
    public Result save(Tag tag) {
        Tag t = tagServer.findByName(tag.getName());
        Tag ta = null;
        if (!ObjectUtils.isEmpty(t) && !t.getId().equals(tag.getId()) && t.getName().equals(tag.getName())) {
            return error("【 " + tag.getName() + " 】标签已经存在");
        }
        if (StringUtils.isEmpty(tag.getId())) {
            tag.setNumber(0);
            ta = tag;
        } else {
            ta = tagServer.findOne(tag.getId());
            ta.setName(tag.getName());
        }
        tagServer.save(ta);
        return success("标签保存成功");
    }

    @DeleteMapping("{id}")
    @ResponseBody
    public Result delete(@PathVariable(value = "id") String id) {
        tagServer.delete(id);
        return success("标签删除成功");
    }

}
