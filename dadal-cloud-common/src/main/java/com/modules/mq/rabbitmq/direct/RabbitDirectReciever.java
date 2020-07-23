/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.appconfig.mq.rabbitmq   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月11日下午7:03:52
 * @version V1.0
 */

package com.modules.mq.rabbitmq.direct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * direct message consumer
 * 
 * @author: Frankjiu
 * @date: 2020年4月11日 下午7:03:52
 */
@Component
public class RabbitDirectReciever {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RabbitListener(queues = "hello-queue")
	public void handler1(String msg) {
		logger.info("======DirectReceiver:{}", msg);
	}

}
