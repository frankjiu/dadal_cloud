/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.cn.nettychat   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2019年11月3日 下午3:05:59   
 * @version V1.0
 */

package com.modules.socket.nettychat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author: Frankjiu
 * @date: 2019年11月3日 下午3:05:59 通道初始化器 用来加载通道处理器(ChannelHandler)
 */

public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {

	// 初始化通道
	// 在这个方法中去加载对应的ChannelHandler
	@Override
	protected void initChannel(SocketChannel arg0) throws Exception {
		// 获取通道, 将一个一个的channelhandler添加到管道中
		ChannelPipeline pipeline = arg0.pipeline();

		// 添加一个http的编解码器
		pipeline.addLast(new HttpServerCodec());

		// 添加对大数据流的支持
		pipeline.addLast(new ChunkedWriteHandler());

		// 添加一个聚合器, 将httpmessage 聚合成 fullhttprequest/response
		pipeline.addLast(new HttpObjectAggregator(1024 * 64));

		// 指定接收请求的路由: 必须以ws为后缀的URL才能访问
		pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

		// 添加自定义的handler
		pipeline.addLast(new Chathandler());

	}

}
