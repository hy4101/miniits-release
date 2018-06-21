package com.miniits.base;

import com.miniits.base.model.entity.User;
import com.miniits.base.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.util.StringUtils;

import static com.miniits.base.utils.MD5Util.hashStr;


/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/3/14
 * @Time: 23:00
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */

@SpringBootApplication
public class MiniitsApplication implements ApplicationListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MiniitsApplication.class);

    @Autowired
    private UserService userService;

    @Value("${admin.userName}")
    private String userName;

    @Value("${admin.password}")
    private String password;

    public static void main(String[] args) {
        SpringApplication.run(MiniitsApplication.class, args);
        LOGGER.info("启动成功!!!");
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        // 在这里可以监听到Spring Boot的生命周期
        if (event instanceof ApplicationReadyEvent) {
            long count = userService.count();
            if (count > 0) {
                return;
            }
            LOGGER.info("app start success");
            LOGGER.info("install admin user");
            String un = userName;
            String pw = password;
            if (StringUtils.isEmpty(un)) {
                un = "admin";
                LOGGER.warn("could not find username,use system default username:{}", un);
            }
            if (StringUtils.isEmpty(pw)) {
                pw = "m_plus";
                LOGGER.warn("could not find password,use system default password:{}", pw);
            }
            LOGGER.info("you system username:{}", un);
            LOGGER.info("you system password:{}", pw);
            LOGGER.info("initialization...");
            User user = new User();
            user.setUserName(un);
            user.setPassword(hashStr(pw));
            user.setUserStatusCode(100000001);
            user.setUserStatusName("启用");
            userService.save(user);
            LOGGER.info("initialization success");
        }
    }
}
