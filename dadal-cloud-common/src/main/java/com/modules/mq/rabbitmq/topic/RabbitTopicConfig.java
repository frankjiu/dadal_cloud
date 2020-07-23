/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.appconfig.mq.rabbitmq.topic   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月11日下午11:31:25
 * @version V1.0
 */

package com.modules.mq.rabbitmq.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Frankjiu
 * @date: 2020年4月11日 下午11:31:25
 */
@Configuration
public class RabbitTopicConfig {

	public static final String TOPICNAME = "frank-topic";

	@Bean
	TopicExchange getTopicExchange() {
		return new TopicExchange(TOPICNAME, true, false);
	}

	@Bean
	Queue getDepartmentOne() {
		return new Queue("departmentOne");
	}

	@Bean
	Queue getDepartmentTwo() {
		return new Queue("departmentTwo");
	}

	@Bean
	Queue getCompany() {
		return new Queue("company");
	}

	@Bean
	Binding departmentOneBinding() {
		return BindingBuilder.bind(getDepartmentOne()).to(getTopicExchange()).with("departmentOne.#");
	}

	@Bean
	Binding departmentTwoBinding() {
		return BindingBuilder.bind(getDepartmentTwo()).to(getTopicExchange()).with("departmentTwo.#");
	}

	@Bean
	Binding companyBinding() {
		return BindingBuilder.bind(getCompany()).to(getTopicExchange()).with("#.company.#");
	}

}
