/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.appconfig.mq   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月10日下午5:30:38
 * @version V1.0
 */

package com.modules.mq.activemq;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Create an ActiveMQ Queue
 * 
 * @author: Frankjiu
 * @date: 2020年4月10日 下午5:30:38
 */
@Configuration
public class ActiveMqConfig {

	@Bean
	public Queue getActiveMQQueue() {
		return new ActiveMQQueue("amq");
	}

}
