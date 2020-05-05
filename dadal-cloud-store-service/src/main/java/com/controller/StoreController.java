/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月18日下午10:07:40
 * @version V1.0
 */

package com.controller;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.User;
import com.utils.StringUtils;

/**
 * @author: Frankjiu
 * @date: 2020年4月18日 下午10:07:40
 */
@RestController
public class StoreController {

	private final Logger logger = LoggerFactory.getLogger(StoreController.class);

	@Autowired
	private DiscoveryClient client;

	@Autowired
	private Registration registration;

	@GetMapping("/storeService")
	public String storeService() throws Exception {
		List<ServiceInstance> services = client.getInstances(registration.getServiceId());

		int time = new Random().nextInt(3000);
		logger.info("sleep time: {}", time);
		Thread.sleep(time);

		if (StringUtils.isNotEmpty(services)) {
			for (ServiceInstance service : services) {
				logger.info("EEEEEEEEEEE Get service info from the regist-center >>>>>> host:{}, serviceId:{}", service.getHost(),
						service.getServiceId());
			}
		}
		return "Now the service(spring.application.name=ddl-cloud-store-service) hash been injected into Eureka-Register!";
	}

	@GetMapping("/storeConsumerFallBackTest")
	public String storeConsumerFallBackTest() {
		throw new RuntimeException(">>>>>>sorry, error occured on server>>>>>>");
	}

	@GetMapping("/hello1")
	public String hello(@RequestParam String name) {
		return "Hello " + name;
	}

	@GetMapping("/hello2")
	public User hello(@RequestHeader String userName, @RequestHeader Integer age) {
		return new User(userName, age);
	}

	@PostMapping("/hello3")
	public String hello(@RequestBody User user) {
		return "User:" + "name:" + user.getUserName() + ", age:" + user.getAge();
	}

}
