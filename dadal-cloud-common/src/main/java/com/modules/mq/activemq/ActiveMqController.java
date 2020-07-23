/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.appconfig.mq   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月10日下午5:52:26
 * @version V1.0
 */

package com.modules.mq.activemq;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * message controller
 * 
 * @author: Frankjiu
 * @date: 2020年4月10日 下午5:52:26
 */
@Controller
public class ActiveMqController {

	@Autowired
	ActiveMqReciever activeMqReciever;

	@GetMapping("/sendmsg")
	public String sendMessage() {
		Message message = new Message();
		message.setContent("二零二零疫情最大!");
		message.setDate(new Date());
		activeMqReciever.send(message);
		return "success";
	}

}
