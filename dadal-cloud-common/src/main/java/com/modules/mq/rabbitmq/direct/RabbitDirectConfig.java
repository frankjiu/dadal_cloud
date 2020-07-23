/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.appconfig.mq.rabbitmq   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月11日下午6:51:55
 * @version V1.0
 */

package com.modules.mq.rabbitmq.direct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Frankjiu
 * @date: 2020年4月11日 下午6:51:55
 */
@Configuration
public class RabbitDirectConfig {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public static final String DIRECTNAME = "frank-direct";
	public static final String QUEUENAME = "hello-queue";
	public static final String ROUTINGKEY = "direct";

	@Bean
	Queue getRabbitMQQueue() {
		logger.info("======RabbitQueue:{} has been created.", QUEUENAME);
		return new Queue(QUEUENAME);
	}

	@Bean
	DirectExchange directExchange() {
		return new DirectExchange(DIRECTNAME, true, false);
	}

	@Bean
	Binding binding() {
		return BindingBuilder.bind(getRabbitMQQueue()).to(directExchange()).with(ROUTINGKEY);
	}

}
