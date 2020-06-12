/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.utils.packutils   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年2月13日下午3:34:27
 * @version V1.0
 */

package com.utils.packdatautils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.imageio.ImageIO;

/**
 * @author: Frankjiu
 * @date: 2020年2月13日 下午3:34:27
 */

public class CopyFile {

	public static void copy(String srcPathStr, String desPathStr) {
		String newFileName = srcPathStr.substring(srcPathStr.lastIndexOf("\\") + 1);
		desPathStr = desPathStr + File.separator + newFileName;
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			// like picture etc...use this method!
			br = new BufferedReader(new FileReader(srcPathStr));
			bw = new BufferedWriter(new FileWriter(desPathStr));
			BufferedImage image = ImageIO.read(new File(srcPathStr));
			ImageIO.write(image, "PNG", new File(desPathStr));

			/*
			 * String line = null; while((line = br.readLine()) != null){
			 * bw.write(line); bw.newLine(); } bw.flush();
			 */

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
