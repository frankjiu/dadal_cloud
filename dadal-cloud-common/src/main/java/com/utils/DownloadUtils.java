package com.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class DownloadUtils {
	public void download(ByteArrayOutputStream byteArrayOutputStream, HttpServletResponse response, String fileName)
			throws IOException {
		response.setContentType("application/octet-stream");
		// please keep up the encoding of the file name with the encoding of the page, or you'll see the messy code!
		fileName = response.encodeURL(new String(fileName.getBytes("ISO8859-1"), "UTF-8"));
		response.addHeader("content-disposition", "attachment;filename=" + fileName);
		response.setContentLength(byteArrayOutputStream.size());
		response.addHeader("Content-Length", "" + byteArrayOutputStream.size());
		// get outputstream
		ServletOutputStream outputstream = response.getOutputStream();
		// write to outputstream
		byteArrayOutputStream.writeTo(outputstream);
		// close outputstream
		byteArrayOutputStream.close();
		// flush data from outputstream and close it
		outputstream.flush();
		outputstream.close();
	}
}
