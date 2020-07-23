/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.appconfig.mq.rabbitmq.fanout   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月11日下午11:13:06
 * @version V1.0
 */

package com.modules.mq.rabbitmq.header;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: Frankjiu
 * @date: 2020年4月11日 下午11:13:06
 */
@Component
public class RabbitHeaderReciever {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RabbitListener(queues = "name-queue")
	public void handler1(byte[] msg) {
		logger.info("======HeaderReciever-name:{}", new String(msg, 0, msg.length));
	}

	@RabbitListener(queues = "age-queue")
	public void handler2(byte[] msg) {
		logger.info("======HeaderReciever-age:{}", new String(msg, 0, msg.length));
	}

}
