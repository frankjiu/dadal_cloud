package com.modules.export2;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @Description: Download Utils
 * @Author: QiuQiang
 * @Date: 2020-07-08
 */

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class DownloadUtils {

    public static final String CODE_ISO8859 = "ISO-8859-1";
    public static final String CODE_UTF8 = "UTF-8";

    // 表头字号, 列宽, 行高 尺寸, 内容字号初始化设置
    public static final int[] CONTENT_SIZE = { 13, 13, 18, 12 };
    // 内容字体设置
    public static final String FONT_SET = "宋体";
    // 单表sheet数据容量
    public static final int CAPACITY = 10 * 1000;

    public void download(ByteArrayOutputStream byteArrayOutputStream, HttpServletResponse response, String fileName)
            throws IOException {
        response.setContentType("application/octet-stream");
        // please keep up the encoding of the file name with the encoding of the page, or you'll see the messy code!
        fileName = response.encodeURL(new String(fileName.getBytes(CODE_UTF8), CODE_UTF8));
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