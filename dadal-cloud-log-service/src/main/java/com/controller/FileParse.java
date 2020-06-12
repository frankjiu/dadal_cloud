/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.controller   
 * @author: Frankjiu
 * @date: 2020年6月1日
 * @version: V1.0
 */

package com.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.entity.AppLog;

/**
 * @Description: 日志文件解析
 * @author: Frankjiu
 * @date: 2020年6月1日
 */
@Component
public class FileParse {

	private static Logger logger = Logger.getLogger(FileParse.class);

	private static final String REGEX = "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])\\s(20|21|22|23|[0-1]\\d):[0-5]\\d:[0-5]\\d,\\d{3}$";

	private static final String SEPARATOR = "1e";

	private static final List<AppLog> list = new ArrayList<>();

	public List<AppLog> parse() {
		String prop = System.getProperty("file.separator");
		String tab = System.getProperty("line.separator");
		String fileName = "readlogs.log";
		String path = System.getProperty("user.dir") + prop + "logs" + prop + "old" + prop + fileName;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
		//DateTimeFormatter
		StringBuilder stb = new StringBuilder();
		BufferedReader bufReader = null;
		AppLog appLog = null;
		//解析日志
		try {
			File file = new File(path);
			InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "GBK");
			bufReader = new BufferedReader(isr);
			String line;

			// 逐行扫描
			while ((line = bufReader.readLine()) != null) {
				boolean isLog = false;
				String logTime = null;

				if (line.trim().length() >= 23) {
					logTime = line.trim().substring(0, 23);
					isLog = logTime.matches(REGEX);
				}

				// 如果为独立单日志行 则进行处理
				if (isLog) {
					// 2015-05-28 16:13:45,873 [main][INFO ] sender.EtermSessionConnectPool - 准备初始化 EtermSessionConnectPool
					// 按分隔符进行 字符串切割
					int leftOneIdx = line.indexOf('[');
					int leftTwoIdx = line.indexOf('[', leftOneIdx + 1);
					int rightOneIdx = line.indexOf(']');
					int rightTwoIdx = line.indexOf(']', rightOneIdx + 1);
					int firstCenterIdx = line.indexOf('-', rightTwoIdx + 1);
					String threadName = line.substring(leftOneIdx + 1, rightOneIdx).trim();
					String logLeval = line.substring(leftTwoIdx + 1, rightTwoIdx).trim();
					String className = line.substring(rightTwoIdx + 1, firstCenterIdx).trim();
					String logInfo = line.substring(firstCenterIdx + 1, line.length()).trim();
					// 切割后将分段数据赋值到对象属性
					appLog = new AppLog();
					stb.setLength(0);
					appLog.setLogTime(format.parse(logTime));
					appLog.setThreadName(threadName);
					appLog.setLogLeval(logLeval);
					appLog.setClassName(className);
					appLog.setLogInfo(logInfo);
					stb.append(logInfo);

				}

				if (!isLog) {
					// 非独立日志详情信息拼接
					stb.append(tab).append(line);
					// 设置日志详情信息
					if (SEPARATOR.equalsIgnoreCase(strTo16(line)) && appLog != null) {
						String str = stb.toString();
						String newStr = str.replaceAll("(\r\n)", "\r\n");
						newStr = str.replaceAll(line, "\r\n");
						appLog.setLogInfo(newStr);
						// 清空stb
						stb.setLength(0);
					} else {
						continue;
					}
				}

				// 记录日志
				list.add(appLog);
				logger.info(appLog.toString());

			}

		} catch (Exception e) {
			logger.info("解析异常!" + e);
		} finally {
			try {
				bufReader.close();
			} catch (IOException e) {
				logger.info(e);
			}
		}
		return list;
	}

	/**
	 * 转换分隔符为16进制值
	 */
	public static String strTo16(String s) {
		StringBuilder stb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			stb = stb.append(Integer.toHexString(ch));
		}
		return stb.toString();
	}

}
