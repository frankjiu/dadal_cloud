/**
 * All rights Reserved, Designed By www.xcompany.com
 * 
 * @Package com.storage
 * @Description: TODO 描述
 * @author: Frankjiu
 * @date: 2020年3月17日下午3:21:56
 * @version V1.0
 */

package com.modules.device;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Frankjiu
 * @date: 2020年3月17日 下午3:21:56
 */
public class MemoryCount {

	private static final Logger logger = LoggerFactory.getLogger(MemoryCount.class);

	public static void main(String[] args) {
		logger.info("内存信息:{}", toMemoryInfo());
	}

	/**
	 * 获取当前jvm内存信息
	 *
	 * @return
	 */
	public static String toMemoryInfo() {
		Runtime currRuntime = Runtime.getRuntime();
		int nFreeMemory = (int) (currRuntime.freeMemory() / 1024 / 1024);
		int nTotalMemory = (int) (currRuntime.totalMemory() / 1024 / 1024);
		return nFreeMemory + "MB/" + nTotalMemory + "MB(free/total)";
	}
}