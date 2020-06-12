/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.utils.packutils   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年2月13日下午3:54:15
 * @version V1.0
 */

package com.utils.packdatautils;

import java.io.File;

/**
 * @author: Frankjiu
 * @date: 2020年2月13日 下午3:54:15
 */

public class DeleteFile {

	public static void delAllFile(File directory) {
		if (directory.isDirectory()) {
			directory.delete();
		} else {
			File[] files = directory.listFiles();
			if (files.length == 0) {
				directory.delete();
				return;
			}

			for (File file : files) {
				if (file.isDirectory()) {
					delAllFile(file);
				} else {
					file.delete();
				}
			}
			directory.delete();
		}

	}

}
