/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.controller   
 * @author: Frankjiu
 * @date: 2020年4月30日
 * @version: V1.0
 */

package com.controller;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: Rabbit Config
 * @author: Frankjiu
 * @date: 2020年4月30日
 */
@Configuration
public class RabbitConfig {

	@Autowired
	@Value("${mq.config.queue.name}")
	private String queueName;

	@Bean
	public Queue timeQueue() {
		return new Queue(queueName);
	}

}
