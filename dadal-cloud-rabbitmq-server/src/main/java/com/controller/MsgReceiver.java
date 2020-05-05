/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.controller   
 * @author: Frankjiu
 * @date: 2020年4月30日
 * @version: V1.0
 */

package com.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: Msg Receiver
 * @author: Frankjiu
 * @date: 2020年4月30日
 */
@Component
@RabbitListener(bindings = @QueueBinding(value = @Queue(value = "${mq.config.queue.name}", autoDelete = "false"), 
				exchange = @Exchange(value = "${mq.config.exchange}",
				type=ExchangeTypes.TOPIC),
				key="${mq.config.routingKey}"
		))
public class MsgReceiver {

	private static final Logger logger = LoggerFactory.getLogger(MsgReceiver.class);

	@RabbitHandler
	public void receive(String message) throws InterruptedException {
		Thread.sleep(3000);
		logger.info("======Received message:{}", message);
	}

}
