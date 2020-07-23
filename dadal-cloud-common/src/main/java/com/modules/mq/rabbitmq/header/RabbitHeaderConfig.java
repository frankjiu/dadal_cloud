/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.appconfig.mq.rabbitmq.fanout   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月11日下午11:03:29
 * @version V1.0
 */

package com.modules.mq.rabbitmq.header;

import java.util.HashMap;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Frankjiu
 * @date: 2020年4月11日 下午11:03:29
 */
@Configuration
public class RabbitHeaderConfig {

	public static final String HEADERNAME = "frank-header";

	@Bean
	HeadersExchange getHeadersExchange() {
		return new HeadersExchange(HEADERNAME, true, false);
	}

	@Bean
	Queue queueName() {
		return new Queue("name-queue");
	}

	@Bean
	Queue queueAge() {
		return new Queue("age-queue");
	}

	@Bean
	Binding bindingName() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("name", "frank");
		return BindingBuilder.bind(queueName()).to(getHeadersExchange()).whereAny(map).match();
	}

	@Bean
	Binding bindingAge() {
		return BindingBuilder.bind(queueAge()).to(getHeadersExchange()).where("age").exists();
	}

}
