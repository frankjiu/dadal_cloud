/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.utils.packutils   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年2月13日下午3:12:40
 * @version V1.0
 */

package com.utils.packdatautils;

/**
 * @author: Frankjiu
 * @date: 2020年2月13日 下午3:12:40
 */

public class Constants {

	private Constants() {
		throw new IllegalStateException("Utility class");
	}

	public static final class Paths {

		private Paths() {
			throw new IllegalStateException("Utility class");
		}

		/**
		 * config_source_file path
		 */
		public static final String PATH = "D:\\test\\svc_pic_020191126.del";

		/**
		 * Thread nums
		 */
		public static final int THREAD_NUM = 6;

		/**
		 * size of the List
		 */
		public static final int SUBLIST_PIC_SIZE = 6;

		/**
		 * zip source files:D:\\test\\ZZXY
		 */
		public static final String FILES_SOURCE = "D:\\test\\ZZXY";

		/**
		 * prePath
		 */
		public static final String PREPATH = "D:\\test";

		/**
		 * source top_parent_file_name
		 */
		public static final String TOP_PARENT_FILE_NAME = "ZZXT";

		/**
		 * source temp_top_parent_file_name
		 */
		public static final String TEMP_TOP_PARENT_FILE_NAME = "ZZXY";
	}
}
