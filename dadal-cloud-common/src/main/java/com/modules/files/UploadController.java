/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.controller   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月1日下午5:30:55
 * @version V1.0
 */

package com.modules.files;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.modules.files.entity.Config;

/**
 * 文件上传
 * 
 * @author: Frankjiu
 * @date: 2020年4月1日 下午5:30:55
 */
@RestController
public class UploadController {

	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	@Autowired
	Config config;

	@PostMapping("/upload")
	public String upload(MultipartFile uploadFile, HttpServletRequest req) {
		if (uploadFile.isEmpty()) {
			return "请先选择文件!";
		}
		// rename the uploaded file
		String oldName = uploadFile.getOriginalFilename();
		String date = sdf.format(new Date());
		String newName = date + UUID.randomUUID().toString().replace("-", "")
				+ oldName.substring(oldName.lastIndexOf('.'), oldName.length());
		try {
			// file save
			File file = new File(new File(config.getUploadFilePath()).getAbsolutePath() + File.separator + newName);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdir();
			}
			uploadFile.transferTo(file);
			return req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + File.separator
					+ newName;
		} catch (IllegalStateException | IOException e) {
			logger.info("上传异常:{}", e.getMessage(), e);
		}
		return "上传失败!";
	}

}
