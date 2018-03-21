package com.miniits.base.controller.admin;

import com.miniits.base.model.User;
import com.miniits.base.model.vo.UserVO;
import com.miniits.base.service.UserService;
import com.miniits.base.utils.BaseController;
import com.miniits.base.utils.ConvertUtil;
import com.miniits.base.utils.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/3/18
 * @Time: 23:02
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
@RestController
@RequestMapping("/admin/user")
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

    @GetMapping("/users")
    public Result getUsers() {
//        Page<User> users = userService.search(pageable);
        return error("用户名或密码错误");
    }

}
