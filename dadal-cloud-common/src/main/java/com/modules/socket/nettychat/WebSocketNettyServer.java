/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.cn.nettychat   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2019年11月3日 下午2:45:57   
 * @version V1.0
 */

package com.modules.socket.nettychat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author: Frankjiu
 * @date: 2019年11月3日 下午2:45:57 websocket 服务器端
 */

public class WebSocketNettyServer {

	public static void main(String[] args) {
		// 主线程池
		NioEventLoopGroup mainGroup = new NioEventLoopGroup();
		// 从线程池
		NioEventLoopGroup subGroup = new NioEventLoopGroup();

		try {
			// 创建Netty服务器启动对象
			ServerBootstrap serverBootstrap = new ServerBootstrap();
			// 初始化服务器启动对象
			serverBootstrap
					// 指定使用上面创建的两个线程池
					.group(mainGroup, subGroup)
					// 指定Netty通道类型
					.channel(NioServerSocketChannel.class)
					// 指定通道初始化器用来加载当channel收到事件消息后如何进行处理
					.childHandler(new WebSocketChannelInitializer());
			// 绑定服务器端口,以同步方式启动服务器
			ChannelFuture future = serverBootstrap.bind(9090).sync();
			// 等待服务器关闭
			future.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 使用优雅的方式关闭服务器
			mainGroup.shutdownGracefully();
			subGroup.shutdownGracefully();
		}
	}

}
