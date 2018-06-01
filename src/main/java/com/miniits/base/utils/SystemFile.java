package com.miniits.base.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/3/3
 * @Time: 12:19
 * <p>
 * Description:
 * ***
 */
public class SystemFile {

    private final static Logger LOGGER = LoggerFactory.getLogger(SystemFile.class);

    /**
     * 判断文件夹是否存在
     * 不存在,则新建
     *
     * @param path
     */
    public static void isPackageExist(String path) {
        File file = new File(path);
        LOGGER.info("qqqqqqqqqqqqqqq" + path);
        LOGGER.info("wwwwwwwwwwwwww" + file.exists());
        if (!file.exists()) {
            LOGGER.info("cccccccccccccccccccccccccccccccccccccc：" + path);
            file.mkdirs();
        }
    }
}
