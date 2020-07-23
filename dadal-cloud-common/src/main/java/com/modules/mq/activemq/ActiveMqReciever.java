/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.appconfig.mq   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月10日下午5:37:02
 * @version V1.0
 */

package com.modules.mq.activemq;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * @author: Frankjiu
 * @date: 2020年4月10日 下午5:37:02
 */
@Component
public class ActiveMqReciever {

	@Autowired
	JmsMessagingTemplate messagingTemplate;

	@Autowired
	private Queue activeMQQueue;

	public void send(Message message) {
		System.out.println("======we will send the message:" + message);
		messagingTemplate.convertAndSend(activeMQQueue, message);
	}

	@JmsListener(destination = "amq")
	public void recieve(Message message) {
		System.out.println("======we have recieved a message:" + message);
	}

}
