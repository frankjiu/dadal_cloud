/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.cn.nionet   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2019年11月4日 下午7:58:55   
 * @version V1.0
 */

package com.modules.socket.nionet;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author: Frankjiu
 * @date: 2019年11月4日 下午7:58:55
 */

public class NIOClient {

	public static void main(String[] args) throws Exception {

		// 1.得到一个网络通道
		SocketChannel channel = SocketChannel.open();
		// 2.设置非阻塞方式
		channel.configureBlocking(false);

		// 3.提供服务器端的IP地址和端口号
		InetSocketAddress address = new InetSocketAddress("127.0.0.1", 9999);

		// 4.连接服务器端, 如果连接不上,执行while重连
		if (!channel.connect(address)) {
			// 未完成连接,则做其它事情
			while (!channel.finishConnect()) {
				System.out.println("去打别的酱油");
			}
		}

		// 5.得到一个缓冲区并存入数据
		String msg = "hello,Server";
		ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
		// 6.发送数据
		channel.write(buffer);

		// 人为阻塞防止服务器端异常
		System.in.read();
	}
}
