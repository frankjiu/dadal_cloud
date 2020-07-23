/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.appconfig.mq.rabbitmq   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月11日下午7:08:50
 * @version V1.0
 */

package com.modules.mq.rabbitmq.header;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: Frankjiu
 * @date: 2020年4月11日 下午7:08:50
 */
@Controller
public class RabbitHeaderController {

	@Autowired
	RabbitTemplate rabbitTemplate;

	@GetMapping("sendHeader")
	public String sendHeader() {
		Message nameMsg = MessageBuilder.withBody("header! name-queue".getBytes()).setHeader("name", "frank").build();
		Message ageMsg = MessageBuilder.withBody("header! age-queue".getBytes()).setHeader("age", "99").build();
		rabbitTemplate.send(RabbitHeaderConfig.HEADERNAME, null, nameMsg);
		rabbitTemplate.send(RabbitHeaderConfig.HEADERNAME, null, ageMsg);
		return "success";
	}

}
