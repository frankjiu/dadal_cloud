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

public class SeparatorUtils {

	private static final Properties PROPERTIES = new Properties(System.getProperties());

	private SeparatorUtils() {
		throw new IllegalStateException("Utility class");
	}

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

}
