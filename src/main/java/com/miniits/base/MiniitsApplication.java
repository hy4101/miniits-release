package com.miniits.base;

import com.miniits.base.model.entity.User;
import com.miniits.base.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.util.StringUtils;

import static com.miniits.base.utils.MD5Util.hashStr;
import static com.miniits.base.utils.SystemDict.GLOBAL_STATUS_YES;


/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/3/14
 * @Time: 23:00
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */

@SpringBootApplication
public class MiniitsApplication extends SpringBootServletInitializer implements ApplicationListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MiniitsApplication.class);

    @Autowired
    private UserService userService;

    @Value("${admin.userName}")
    private String userName;

    @Value("${admin.password}")
    private String password;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MiniitsApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(MiniitsApplication.class, args);
        LOGGER.info("started!!!");
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationReadyEvent) {
            LOGGER.info("app start success");
            long count = userService.count();
            if (count > 0) {
                return;
            }
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
            user.setUserStatusCode(GLOBAL_STATUS_YES);
            user.setUserStatusName("启用");
            userService.save(user);
            LOGGER.info("initialization success");
        }
    }
}
