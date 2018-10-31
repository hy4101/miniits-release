package com.miniits.base.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private static final String LINUX_PATH = "/data/idnum/";

    private static final String WINDOWS_PATH = "c:\\m-plus\\";

    private static String regEx = "[ `~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";

    public static String validateFileName(String fileName) {
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(fileName);
        return matcher.replaceAll("_");
    }

    /**
     * 创建模板和html文件夹
     *
     * @param str
     * @return
     */

    public static String createTemplateFolderAndHtmlFolder(String str) {
        String path = null;
        if (OsUtil.isLinux()) {
            path = LINUX_PATH + str + "/";
            File uploadDir = new File(path);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
        }

        if (OsUtil.isWindows()) {
            path = WINDOWS_PATH + str + "\\";
            File uploadDir = new File(path);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
        }
        return path;
    }

    /**
     * 下载文件
     *
     * @param response
     * @param fileName 文件名称
     * @throws IOException
     */
    public static void exportFile(HttpServletResponse response, String fileName, String s)
            throws IOException {
        response.setContentType("application/csv;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;  filename=" + new String(fileName.getBytes("UTF-8"), "ISO8859-1"));

        ByteArrayInputStream in = null;
        try {
            in = new ByteArrayInputStream(s.getBytes());
            int len = 0;
            byte[] buffer = new byte[1024];
            response.setCharacterEncoding("UTF-8");
            OutputStream out = response.getOutputStream();
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 判断文件夹是否存在
     * 不存在,则新建
     *
     * @param path
     */
    public static void isPackageExist(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

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
        logger.info("delete dir:{}", deletePath);
        try {
            File file = new File(deletePath);
            if (!file.isDirectory()) {
                file.delete();
            } else if (file.isDirectory()) {
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File delfile = new File(deletePath + "/" + filelist[i]);
                    if (!delfile.isDirectory()) {
                        delfile.delete();
                        logger.info(delfile.getAbsolutePath() + " delete success");
                    } else if (delfile.isDirectory()) {
                        deletefile(deletePath + "/" + filelist[i]);
                    }
                }
                file.delete();
                logger.info(file.getAbsolutePath() + " delete success");
            }
        } catch (Exception e) {
            logger.error("delete file Exception:" + e.getMessage());
        }
        return true;
    }
}