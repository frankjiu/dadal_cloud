/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.appconfig.mq.rabbitmq.fanout   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月11日下午11:13:06
 * @version V1.0
 */

package com.modules.mq.rabbitmq.fanout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: Frankjiu
 * @date: 2020年4月11日 下午11:13:06
 */
@Component
public class RabbitFanoutReciever {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RabbitListener(queues = "queue-one")
	public void handler1(String message) {
		logger.info("======RabbitFanoutReciever-handler1:{}", message);
	}

	@RabbitListener(queues = "queue-two")
	public void handler2(String message) {
		logger.info("======RabbitFanoutReciever-handler2:{}", message);
	}

}
