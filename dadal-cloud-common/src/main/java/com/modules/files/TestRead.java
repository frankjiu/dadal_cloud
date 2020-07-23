/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.test   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2019年10月31日 上午11:42:24   
 * @version V1.0
 */

package com.modules.files;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

/**
 * @author: Frankjiu
 * @date: 2019年10月31日 上午11:42:24
 */
public class TestRead {
	@Test
	public void testReadBigFile() throws URISyntaxException, IOException {

		CharBuffer charBuffer = null;

		ClassLoader classLoader = TestRead.class.getClassLoader();
		// 方式一: 使用文件磁盘全路径
		// Path path =
		// Paths.get(classLoader.getResource("D:\\wx_workspace\\asreping\\src\\main\\resources\\bigfiles.txt").getPath());
		// 方式二: 使用系统资源查找文件资源路径
		URI uri = classLoader.getSystemResource("PowerDesigner.doc").toURI();
		String mainPath = Paths.get(uri).toString();
		Path path = Paths.get(mainPath);
		MappedByteBuffer mappedByteBuffer = null;
		try {
			FileChannel fileChannel = FileChannel.open(path);
			mappedByteBuffer = fileChannel.map(MapMode.READ_ONLY, 0, fileChannel.size());
			if (mappedByteBuffer != null) {
				charBuffer = Charset.forName("UTF-8").decode(mappedByteBuffer);
			}
			// System.out.println(charBuffer.asReadOnlyBuffer().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		// CharBuffer charBuffer2 = CharBuffer.wrap("沉默王二，《Web全栈开发进阶之路》作者");
		CharBuffer charBuffer2 = Charset.forName("UTF-8").decode(mappedByteBuffer);

		Path path2 = Paths.get("D:\\wx_workspace\\asreping\\src\\main\\resources\\bigfiles_copied.txt");

		try (FileChannel fileChannel = FileChannel.open(path2, StandardOpenOption.READ, StandardOpenOption.WRITE,
				StandardOpenOption.TRUNCATE_EXISTING)) {
			mappedByteBuffer = fileChannel.map(MapMode.READ_WRITE, 0, 1024);

			if (mappedByteBuffer != null) {
				mappedByteBuffer.put(Charset.forName("UTF-8").encode(charBuffer2));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
