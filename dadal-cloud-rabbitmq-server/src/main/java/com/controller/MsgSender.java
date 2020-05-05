/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.controller   
 * @author: Frankjiu
 * @date: 2020年4月30日
 * @version: V1.0
 */

package com.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description: Msg Sender
 * @author: Frankjiu
 * @date: 2020年4月30日
 */
//@RestController
@Component
public class MsgSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	// exchange-name
	@Value("${mq.config.exchange}")
	private String exchange;

	// routingKey-name
	@Value("${mq.config.routingKey}")
	private String routingKey;

	/**
	 * send message
	 */
	@GetMapping("/send")
	public String send(String message) {

		int number = new Random().nextInt(10000);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStr = formatter.format(new Date());
		message = "NO" + number + " msg at time: " + timeStr;

		this.rabbitTemplate.convertAndSend(this.exchange, this.routingKey, message);
		return "Message [" + message + "] has been sent!";
	}
}
