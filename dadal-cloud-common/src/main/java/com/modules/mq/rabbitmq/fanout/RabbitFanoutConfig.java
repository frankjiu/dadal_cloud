/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.appconfig.mq.rabbitmq.fanout   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月11日下午11:03:29
 * @version V1.0
 */

package com.modules.mq.rabbitmq.fanout;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Frankjiu
 * @date: 2020年4月11日 下午11:03:29
 */
@Configuration
public class RabbitFanoutConfig {

	public static final String FANOUTNAME = "frank-fanout";

	@Bean
	FanoutExchange getFanoutExchange() {
		return new FanoutExchange(FANOUTNAME, true, false);
	}

	@Bean
	Queue getQueueOne() {
		return new Queue("queue-one");
	}

	@Bean
	Queue getQueueTwo() {
		return new Queue("queue-two");
	}

	@Bean
	Binding bindingOne() {
		return BindingBuilder.bind(getQueueOne()).to(getFanoutExchange());
	}

	@Bean
	Binding bindingTwo() {
		return BindingBuilder.bind(getQueueTwo()).to(getFanoutExchange());
	}

}
