package com.miniits.base.controller.admin;

import com.miniits.base.model.entity.User;
import com.miniits.base.model.vo.UserVO;
import com.miniits.base.mysql.Pageable;
import com.miniits.base.service.UserService;
import com.miniits.base.utils.BaseController;
import com.miniits.base.utils.ConvertUtil;
import com.miniits.base.utils.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/3/18
 * @Time: 23:02
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
@Controller
@RequestMapping("/admin/users")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestParam(value = "user_name") String userName,
                        @RequestParam(value = "pass_word") String password) {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName, password);
        Subject subject = SecurityUtils.getSubject();
        subject.login(usernamePasswordToken);
        User user = userService.findByUserName(userName);
        if (!ObjectUtils.isEmpty(user)) {
            UserVO userVO = ConvertUtil.toVO(user, UserVO.class);
            return success(userVO);
        }
        return error("用户名或密码错误");
    }

    @GetMapping("init")
    public String init(ModelMap modelMap) {
        modelMap.put("active", "users");
        return "admin/views/system/Users";
    }

    @GetMapping
    @ResponseBody
    public Result users(Pageable pageable) {
        Page<User> users = userService.search(pageable);
        List<UserVO> userVOS = (List<UserVO>) ConvertUtil.toVOS(users.getContent(), UserVO.class);
        return page(userVOS).page(pageable.getPageSize()).size(pageable.getPageNumber()).totalCount(users.getTotalElements()).total(users.getTotalElements());
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Result deleteUser(@PathVariable(value = "id") String id) {
        userService.delete(id);
        return success("用户删除成功");
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public Result getUser(@PathVariable(value = "id") String id) {
        return success(ConvertUtil.toVO(userService.findOne(id), UserVO.class));
    }

    @PostMapping("/save")
    @ResponseBody
    public Result saveUser(User user) {
        if (!ObjectUtils.isEmpty(userService.findByUserName(user.getUserName()))) {
            return error(user.getUserName() + "已经存在，无法新增");
        }
        if (StringUtils.isEmpty(user.getUserStatusCode())){
            user.setUserStatusCode(100000002);
            user.setUserStatusName("禁用");
        }
        return success(ConvertUtil.toVO(userService.save(user), UserVO.class));
    }

    @PostMapping("/change/status")
    @ResponseBody
    public Result changeStatus(@RequestParam(value = "id") String id, @RequestParam(value = "status") Integer status) {
        userService.changeStatus(id, status);
        return success("更改成功");
    }

}
