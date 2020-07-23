/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.appconfig.mq.rabbitmq   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月11日下午7:08:50
 * @version V1.0
 */

package com.modules.mq.rabbitmq.fanout;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: Frankjiu
 * @date: 2020年4月11日 下午7:08:50
 */
@Controller
public class RabbitFanoutController {

	@Autowired
	RabbitTemplate rabbitTemplate;

	@GetMapping("sendFanout")
	public String sendFanout() {
		rabbitTemplate.convertAndSend(RabbitFanoutConfig.FANOUTNAME, null, "hello fanout!");
		return "success";
	}

}
