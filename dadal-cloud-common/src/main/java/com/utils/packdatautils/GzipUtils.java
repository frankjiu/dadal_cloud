/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.utils.packutils   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年2月13日下午3:59:33
 * @version V1.0
 */

package com.utils.packdatautils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Frankjiu
 * @date: 2020年2月13日 下午3:59:33
 */

public class GzipUtils {

	private static final Logger logger = LoggerFactory.getLogger(GzipUtils.class);

	public static void compress(String sourceFolder, String tartGzPath) {
		createTarFile(sourceFolder, tartGzPath);
	}

	/**
	 * @param sourceFolder
	 * @param tartGzPath void
	 */
	private static void createTarFile(String sourceFolder, String tartGzPath) {
		FileOutputStream fos = null;
		GZIPOutputStream gos = null;
		TarArchiveOutputStream tarOs = null;
		try {
			fos = new FileOutputStream(tartGzPath);
			gos = new GZIPOutputStream(new BufferedOutputStream(fos));
			tarOs = new TarArchiveOutputStream(gos);
			tarOs.setLongFileMode(TarArchiveOutputStream.LONGFILE_POSIX);
			addFileToTarGz(sourceFolder, "", tarOs);
		} catch (Exception e) {
			logger.info(e.getMessage(), e);
		} finally {
			try {
				/**
				 * tarOs.closeArchiveEntry();
				 */
				if (tarOs != null) {
					tarOs.close();
				}
				if (gos != null) {
					gos.close();
				}
				if (fos != null) {
					fos.close();
				}

			} catch (Exception e) {
				logger.info(e.getMessage(), e);
			}
		}

	}

	private static void addFileToTarGz(String filePath, String parent, TarArchiveOutputStream tarArchive) throws IOException {
		File file = new File(filePath);
		String entryName = parent + file.getName();
		tarArchive.putArchiveEntry(new TarArchiveEntry(file, entryName));

		if (file.isFile()) {
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			IOUtils.copy(bis, tarArchive);
			tarArchive.closeArchiveEntry();
			bis.close();
		} else if (file.isDirectory()) {
			tarArchive.closeArchiveEntry();
			File[] files = file.listFiles();
			for (File f : files) {
				addFileToTarGz(f.getAbsolutePath(), entryName + File.separator, tarArchive);
			}
		}

	}

}
