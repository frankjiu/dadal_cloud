/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.appconfig.mq.rabbitmq.fanout   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月11日下午11:13:06
 * @version V1.0
 */

package com.modules.mq.rabbitmq.topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: Frankjiu
 * @date: 2020年4月11日 下午11:13:06
 */
@Component
public class RabbitTopicReciever {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RabbitListener(queues = "departmentOne")
	public void handler1(String message) {
		logger.info("======departmentOneTopicReciever-handler1:{}", message);
	}

	@RabbitListener(queues = "departmentTwo")
	public void handler2(String message) {
		logger.info("======departmentTwoTopicReciever-handler2:{}", message);
	}

	@RabbitListener(queues = "company")
	public void handler3(String message) {
		logger.info("======companyTopicReciever-handler3:{}", message);
	}

}
