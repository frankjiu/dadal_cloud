/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.controller   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年1月30日下午7:09:22
 * @version V1.0
 */

package com.modules.download.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Frankjiu
 * @date: 2020年1月30日 下午7:09:22 文件下载
 */

public class DownLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(DownLoadServlet.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		InputStream is = null;
		ServletOutputStream os = null;
		try {
			// 接收参数
			String fileName = request.getParameter("filename");
			// 处理请求文件名中文乱码(服务器默认码表, 前台页面码表)
			fileName = new String(fileName.getBytes("iso8859-1"), "utf-8");
			// 处理下载文件名中文乱码(客户端码表, 响应头码表)
			String downLoadFileName = new String(fileName.getBytes("gbk"), "iso8859-1");

			// 设置下载方式
			response.setHeader("Content-Disposition", "attachment;filename=" + downLoadFileName);
			// 文件下载
			is = this.getServletContext().getResourceAsStream("/download/" + fileName);
			os = response.getOutputStream();
			int len = -1;
			byte[] arrLen = new byte[1024];
			while ((len = is.read(arrLen)) != -1) {
				os.write(arrLen, 0, len);
			}
			os.flush();
		} catch (Exception e) {
			log.info("下载出现异常!", e);
		} finally {
			// 关闭输入流
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
					log.info("关闭输入流异常!", e);
				}
			}
			// 关闭输出流
			if (os != null) {
				try {
					os.close();
				} catch (Exception e) {
					log.info("关闭输出流异常!", e);
				}
			}

		}
	}

}
