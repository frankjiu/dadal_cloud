/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.utils.packutils   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年2月13日下午5:03:29
 * @version V1.0
 */

package com.utils.packdatautils;

import java.io.File;
import java.util.List;

/**
 * @author: Frankjiu
 * @date: 2020年2月13日 下午5:03:29
 */

public class OperateThread implements Runnable {

	private String threadName;
	private List<List<String>> averageList;
	private int k;

	public OperateThread() {
		super();
	}

	public OperateThread(String threadName, List<List<String>> averageList, int k) {
		super();
		this.threadName = threadName;
		this.averageList = averageList;
		this.k = k;
	}

	@Override
	public void run() {
		long start = System.currentTimeMillis();

		List<String> subpathList = averageList.get(k);

		int size = subpathList.size();
		String desPicFileParentPath = null;
		try {
			for (int i = 0; i < size; i++) {
				// get split sublist
				File picFile = new File(subpathList.get(i));
				String srcPicFilePath = Constants.Paths.PREPATH + picFile.getPath();
				desPicFileParentPath = Constants.Paths.PREPATH + picFile.getParentFile().getPath()
						.replace(Constants.Paths.TOP_PARENT_FILE_NAME, Constants.Paths.TEMP_TOP_PARENT_FILE_NAME + k);

				File desParentFile = new File(desPicFileParentPath);
				if (!desParentFile.exists()) {
					desParentFile.mkdirs();
				}
				// copy to dir
				CopyFile.copy(srcPicFilePath, desPicFileParentPath);
			}

			// rar file
			File subParentFile = new File(desPicFileParentPath);
			String pubParentFilePath = subParentFile.getParent();

			// Mothod-one
			GzipUtils.compress(pubParentFilePath, pubParentFilePath + "tar.gz");

			// Mothod-two
			// ZipUtils.zip(pubParentFilePath + ".zip", new
			// File(pubParentFilePath));

			long end = System.currentTimeMillis();
			System.out.println(threadName + k + ">>>>>> Gzip cost time " + (end - start) / 1000 + "s >>>>>>");

			// delete dir
			DeleteFile.delAllFile(new File(pubParentFilePath));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// latch.countDown();
		}

	}

}
