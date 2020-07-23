/**
 * All rights Reserved, Designed By www.xcompany.com
 * 
 * @Package com.appconfig.websocket
 * @Description: TODO 描述
 * @author: Frankjiu
 * @date: 2020年4月7日下午11:37:14
 * @version V1.0
 */

package com.modules.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * websocket configuration
 * 
 * @author: Frankjiu
 * @date: 2020年4月7日 下午11:37:14
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/queue/", "/topic/");
		registry.setApplicationDestinationPrefixes("/speak");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/speaking").withSockJS();
	}

}