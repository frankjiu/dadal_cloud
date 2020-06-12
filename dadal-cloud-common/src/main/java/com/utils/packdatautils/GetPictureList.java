/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.utils.packutils   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年2月13日下午6:22:33
 * @version V1.0
 */

package com.utils.packdatautils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Frankjiu
 * @date: 2020年2月13日 下午6:22:33 return the PictureList
 */

public class GetPictureList {

	private static final Logger logger = LoggerFactory.getLogger(GetPictureList.class);

	private GetPictureList() {
		throw new IllegalStateException("Utility class");
	}

	public static List<Picture> getList(String path) throws FileNotFoundException {
		List<Picture> pictureList = new ArrayList<>();
		FileInputStream fis = null;
		BufferedReader bf = null;
		try {
			File file = new File(path);
			fis = new FileInputStream(file);

			bf = new BufferedReader(new InputStreamReader(fis, "GBK"));
			String len = null;

			while ((len = bf.readLine()) != null) {
				String[] str = len.split("\\,");
				Picture picture = new Picture();
				picture.setServerName(str[0].substring(1, str[0].length() - 1));
				picture.setDetailPicture(str[1].substring(1, str[1].length() - 1));
				picture.setSimplePicture(str[2].substring(1, str[2].length() - 1));
				pictureList.add(picture);
			}

		} catch (IOException e) {
			logger.info(e.getMessage(), e);
		} finally {
			try {
				if (bf != null) {
					bf.close();
				}
			} catch (IOException e) {
				logger.info(e.getMessage(), e);
			}
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				logger.info(e.getMessage(), e);
			}
		}
		return pictureList;

	}
}
