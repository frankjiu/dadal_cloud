/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.utils   
 * @author: Frankjiu
 * @date: 2020年4月29日
 * @version: V1.0
 */

package com.utils;

/**
 * @Description: SeparatorUtils
 * @author: Frankjiu
 * @date: 2020年4月29日
 */

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SeparatorUtils {

	private static final Properties PROPERTIES = new Properties(System.getProperties());

	private static final Logger logger = LoggerFactory.getLogger(SeparatorUtils.class);

	/**
	 * @return path separator like / or \ ; Linux is / and Windows is \ , / is
	 *         ok too in Windows, if you want to use \ in Windows, please type
	 *         \\, as '\' is a translator
	 */
	public static String getFileSeparator() {
		return PROPERTIES.getProperty("file.separator");
	}

	/**
	 * @return line separator like \n or \r\n
	 */
	public static String getLineSeparator() {
		return PROPERTIES.getProperty("line.separator");
	}

	/**
	 * @return path separator like : or ;
	 */
	public static String getPathSeparator() {
		return PROPERTIES.getProperty("path.separator");
	}

	public static void main(String[] args) {
		logger.info("File separator:{}", SeparatorUtils.getFileSeparator().equals("\\"));
		logger.info("Line separator:{}", SeparatorUtils.getLineSeparator().equals("\r\n"));
		logger.info("Path separator:{}", SeparatorUtils.getPathSeparator().equals(";"));
	}
}
