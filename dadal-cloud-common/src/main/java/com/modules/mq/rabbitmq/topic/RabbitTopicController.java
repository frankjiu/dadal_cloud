/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.appconfig.mq.rabbitmq   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月11日下午7:08:50
 * @version V1.0
 */

package com.modules.mq.rabbitmq.topic;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: Frankjiu
 * @date: 2020年4月11日 下午7:08:50
 */
@Controller
public class RabbitTopicController {

	@Autowired
	RabbitTemplate rabbitTemplate;

	@GetMapping("sendTopic")
	public String sendTopic() {
		rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME, "departmentOne-seller", "部门一:销售部...");
		rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME, "departmentOne-plan", "部门一:策划部...");
		rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME, "departmentTwo-afterseller", "部门二:售后部...");
		rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME, "departmentTwo-produce", "部门二:生产部...");
		rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME, "gz-company-departmentOne", "广州分公司:广州分部-销售部...");
		rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME, "sh-company-part", "上海分公司:上海分部...");
		rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME, "bj-company-part", "北京分公司:北京分部...");
		rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME, "company-center", "总公司:总部...");
		return "success";
	}

}
