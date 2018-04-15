package com.miniits.base.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 判断文件/文件夹是否存在
     *
     * @param path
     * @return
     */
    public static boolean fileExists(String path) {
        if (StringUtils.isEmpty(path)) {
            return false;
        }
        File file = new File(path);
        if (file.exists() && file.isFile()) {
            return true;
        }
        return false;
    }

    /**
     * 删除单个文件
     *
     * @param deleteFile
     * @return
     */
    public static boolean deleteFile(String deleteFile) {
        File file = new File(deleteFile);
        if (file.exists() && file.isFile()) {
            boolean result = file.delete();
            int tryCount = 0;
            while (!result && tryCount++ < 10) {
                System.gc();
                result = file.delete();
            }
            return result;
        } else {
            return false;
        }
    }

    /**
     * 删除文件夹下的所有文件夹和文件
     *
     * @param deletePath
     * @return Boolean
     */
    public static boolean deletefile(String deletePath) {
        try {
            File file = new File(deletePath);
            if (!file.isDirectory()) {
                file.delete();
            } else if (file.isDirectory()) {
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File delfile = new File(deletePath + "\\" + filelist[i]);
                    if (!delfile.isDirectory()) {
                        delfile.delete();
                        logger.info(delfile.getAbsolutePath() + "delete success");
                    } else if (delfile.isDirectory()) {
                        deletefile(deletePath + "\\" + filelist[i]);
                    }
                }
                logger.info(file.getAbsolutePath() + "delete success");
                file.delete();
            }
        } catch (Exception e) {
            logger.error("delete file Exception:" + e.getMessage());
        }
        return true;
    }
}