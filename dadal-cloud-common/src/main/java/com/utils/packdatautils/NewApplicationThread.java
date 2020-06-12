/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.utils.packutils   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年2月13日下午4:43:11
 * @version V1.0
 */

package com.utils.packdatautils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Frankjiu
 * @date: 2020年2月13日 下午4:43:11
 */

public class NewApplicationThread {

	public static void main(String[] args) throws RuntimeException, Exception {
		/**
		 * get the config_file
		 */
		File file = new File(Constants.Paths.PATH);
		if (!file.exists()) {
			try {
				throw new FileNotFoundException("config file is not exist!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * analysis the config_file
		 */
		System.out.println("config_file_path: " + file.getPath());

		/**
		 * get the picture_list list
		 */
		List<Picture> list = GetPictureList.getList(file.getPath());

		/**
		 * get the picture_path_list data
		 */
		List<String> pathList = new ArrayList<String>();

		for (int i = 0; i < list.size(); i++) {
			Picture picture = list.get(i);
			String detailPath = picture.getDetailPicture();
			String simplePath = picture.getSimplePicture();
			pathList.add(detailPath);
			pathList.add(simplePath);
		}

		/**
		 * split the list
		 */
		int total = pathList.size();
		int batch_size = total / Constants.Paths.THREAD_NUM;

		List<List<String>> averageList = ListUtils.partition(pathList, batch_size + 1);
		int size = averageList.size();
		System.out.println("total size:" + size);

		Thread[] threads = new Thread[Constants.Paths.THREAD_NUM];
		for (int i = 0; i < Constants.Paths.THREAD_NUM; i++) {
			OperateThread opThread = new OperateThread("Thread-", averageList, i);
			Thread thread = new Thread(opThread);
			threads[i] = thread;
		}

		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}

		for (int i = 0; i < threads.length; i++) {
			threads[i].join();
		}

		System.out.println("MainThread Finished Execute!");
		System.exit(0);
	}

}
