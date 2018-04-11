package com.miniits.base.utils;

import java.io.File;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/4/11
 * @Time: 23:15
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
public class FileUtil {

    public static boolean fileExists(String path) {
        File file = new File(path);
        if (file.exists() && file.isFile()) {
            return true;
        }
        return false;
    }
}
