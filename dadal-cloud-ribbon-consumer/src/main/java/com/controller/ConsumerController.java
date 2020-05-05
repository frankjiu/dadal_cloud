/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月18日下午8:57:09
 * @version V1.0
 */

package com.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.User;
import com.service.ConsumerService;

/**
 * @Description: consumer
 * @author: Frankjiu
 * @date: 2020年4月21日
 */
@RestController
public class ConsumerController {

	private final Logger logger = LoggerFactory.getLogger(ConsumerController.class);

	@Autowired
	ConsumerService consumerService;

	@GetMapping("/storeConsumer")
	public String storeConsumer() {
		long start = System.currentTimeMillis();
		String result = consumerService.storeConsumer();
		long end = System.currentTimeMillis();
		logger.info("cost time:{}s", (end - start) / 1000);
		return result;
	}

	@PostMapping("/add")
	public String add(User user) {
		user = new User("frank", 30);
		return consumerService.add(user);
	}

	@PutMapping("/put")
	public void put(User user, String id) {
		user = new User("frank", 30);
		consumerService.put(user, id);
	}

	@DeleteMapping("/delete")
	public void delete(String id) {
		consumerService.delete(id);
	}

}
