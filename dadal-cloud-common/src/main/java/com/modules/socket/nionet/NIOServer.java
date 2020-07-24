/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.cn.nionet   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2019年11月4日 下午8:31:23   
 * @version V1.0
 */

package com.modules.socket.nionet;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author: Frankjiu
 * @date: 2019年11月4日 下午8:31:23
 */

public class NIOServer {

	public static void main(String[] args) throws Exception {

		// 1.得到一个ServerSocketChannel对象
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

		// 2.得到一个selector间谍对象
		Selector selector = Selector.open();
		// 3.绑定一个端口号
		serverSocketChannel.bind(new InetSocketAddress(9999));
		// 4.设置非阻塞方式
		serverSocketChannel.configureBlocking(false);
		// 5.把对象serverSocketChannel注册给selector对象
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		// 6.干活
		while (true) {
			// 6.1监控客户端
			if (selector.select(2000) == 0) {
				System.out.println("Server:没有客户端连接,就做其它事.");
				continue;
			}
			// 6.2 得到SelectionKey,判断通道里的事件
			Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
			while (keyIterator.hasNext()) {
				SelectionKey selectionKey = (SelectionKey) keyIterator.next();
				// 客户端连接事件
				if (selectionKey.isAcceptable()) {
					System.out.println("OP_ACCEPT");
					SocketChannel socketChannel = serverSocketChannel.accept();
					socketChannel.configureBlocking(false);
					socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
				}
				// 客户端读取数据事件
				if (selectionKey.isReadable()) {
					SocketChannel channel = (SocketChannel) selectionKey.channel();
					ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
					channel.read(buffer);
					System.out.println("客户端发来数据:" + new String(buffer.array()));
				}

				// 手动从集合中移除当前key, 防止重复处理
				keyIterator.remove();
			}
		}

	}

}
