package com.miniits.base.utils;

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

    /**
     * 判断文件夹是否存在
     * 不存在,则新建
     *
     * @param path
     */
    public static void isPackageExist(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
    }
}
