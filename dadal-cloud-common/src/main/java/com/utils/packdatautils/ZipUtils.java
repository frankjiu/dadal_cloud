/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.utils.packutils   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年2月13日下午5:32:09
 * @version V1.0
 */

package com.utils.packdatautils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author: Frankjiu
 * @date: 2020年2月13日 下午5:32:09 Zip-utils
 */

public class ZipUtils {

	public static void zip(String zipfileName, File inputFile) throws IOException {
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfileName));
		BufferedOutputStream bos = new BufferedOutputStream(out);

		zip(out, inputFile, inputFile.getName(), bos);
		bos.close();
		out.close();
	}

	/**
	 * @param out
	 * @param inputFile
	 * @param name
	 * @param bos
	 *            void
	 */

	private static void zip(ZipOutputStream out, File f, String base, BufferedOutputStream bos) throws IOException {
		if (f.isDirectory()) {
			File[] files = f.listFiles();
			if (files.length == 0) {
				out.putNextEntry(new ZipEntry(base + "/"));
			}

			for (int i = 0; i < files.length; i++) {
				zip(out, files[i], base + "/" + files[i].getName(), bos);
			}

		} else {
			out.putNextEntry(new ZipEntry(base));
			FileInputStream fis = new FileInputStream(f);
			BufferedInputStream bis = new BufferedInputStream(fis);

			int b;
			while ((b = bis.read()) != -1) {
				bos.write(b);
				bos.flush();
			}
			bis.close();
			fis.close();
		}
	}

	public static void main(String[] args) {
		try {
			zip("xxx.zip", new File("xxx"));
			System.out.println("OK!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
