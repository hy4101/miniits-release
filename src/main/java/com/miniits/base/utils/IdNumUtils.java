package com.miniits.base.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
   * JAVA版本的自动生成有规则的订单号(或编号)
   * 生成的格式是: 200908010001 前面几位为当前的日期,后面五位为系统自增长类型的编号
   * 原理:
   *      1.获取当前日期格式化值;
   *      2.读取文件,上次编号的值+1最为当前此次编号的值
   *      (新的一天会重新从1开始编号)
   *
   *      存储自动编号值的文件如下(文件名: EveryDaySerialNumber.dat)
   *
   * @author Tod.xie
   */

public class IdNumUtils {

	//日志
	protected static Logger logger = LoggerFactory.getLogger(IdNumUtils.class);

	private static final String LINUX_PATH = "/data/idnum/";

	private static final String WINDOWS_PATH = "c:\\m-plus\\";

	private static final String FILENAME = "EveryDaySerialNumber.dat";

	private static String FILEPATH = "";

	static{
		if(OsUtil.isLinux()){
			FILEPATH = LINUX_PATH + FILENAME;
	        //创建目录
			File uploadDir = new File(LINUX_PATH);
	        if (!uploadDir.exists()) {
	            uploadDir.mkdirs();
	        }
		}

		if(OsUtil.isWindows()){
			FILEPATH = WINDOWS_PATH + FILENAME;
	        //创建目录
			File uploadDir = new File(WINDOWS_PATH);
	        if (!uploadDir.exists()) {
	            uploadDir.mkdirs();
	        }
		}
	}
}

