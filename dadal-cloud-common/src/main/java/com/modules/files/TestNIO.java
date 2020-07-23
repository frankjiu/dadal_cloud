/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.cn.niowritetofile   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2019年11月4日 下午5:38:54   
 * @version V1.0
 */

package com.modules.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.junit.Test;

/**
 * @author: Frankjiu
 * @date: 2019年11月4日 下午5:38:54
 */

public class TestNIO {

	// 往本地文件中写数据
	@Test
	public void test1() throws Exception {

		// 1.创建输出流
		FileOutputStream fos = new FileOutputStream("hello.txt");
		// 2.从流中得到一个通道
		FileChannel channel = fos.getChannel();
		// 3.获取一个缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		// 4.往缓冲区中存入数据
		String str = "hello, nio";
		buffer.put(str.getBytes());
		// 5.翻转缓冲区
		buffer.flip();
		// 6.将缓冲区写入通道
		channel.write(buffer);
		// 7.关闭流
		fos.close();
	}

	// 从本地文件中读取数据
	@Test
	public void test2() throws Exception {

		File file = new File("hello.txt");
		// 1.创建输入流
		FileInputStream fis = new FileInputStream(file);
		// 2.获取通道
		FileChannel channel = fis.getChannel();
		// 3.准备一个缓冲区
		ByteBuffer buffer = ByteBuffer.allocate((int) file.length());
		// 4.从通道读取数据并存到缓冲区
		channel.read(buffer);
		System.out.println(new String(buffer.array()));
		// 5.关闭流
		fis.close();

	}

	// 使用NIO实现文件复制
	@Test
	public void test3() throws Exception {

		// 创建两个流
		FileInputStream fis = new FileInputStream("E:\\1.Media\\旧金山 [高质量和大小].mp4");
		FileOutputStream fos = new FileOutputStream("E:\\test\\test.mp4");

		// 获取两个通道
		FileChannel sourceChannel = fis.getChannel();
		FileChannel destChannel = fos.getChannel();
		long start = System.currentTimeMillis();
		// 读写(to: 源-->目标 from: 目标<--源)
		sourceChannel.transferTo(0, sourceChannel.size(), destChannel);
		long end = System.currentTimeMillis();
		System.out.println("写入完毕,耗时" + (end - start) / 1000 + "秒");
		// 关闭流
		fis.close();
		fos.close();

	}

}
