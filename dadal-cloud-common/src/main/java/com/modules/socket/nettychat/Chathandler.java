/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.cn.nettychat   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2019年11月3日 下午4:14:17   
 * @version V1.0
 */

package com.modules.socket.nettychat;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author: Frankjiu
 * @date: 2019年11月3日 下午4:14:17 websocket 客户端
 */

public class Chathandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

	// 用来保存所有的客户端连接
	private static ChannelGroup client = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	// 当channel中有新的事件消息会自动调用
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
		// 获取客户端发送过来的文本消息
		String text = msg.text();
		System.out.println("接收到的消息为: " + text);

		for (Channel channel : client) {
			// 将消息发送到所有客户端
			channel.writeAndFlush(new TextWebSocketFrame(formatter.format(new Date()) + ":" + text));
		}

	}

	// 当有新的客户端连接服务器后, 会自动调用这个方法
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		// 将新的通道加入clients
		client.add(ctx.channel());
	}

}
