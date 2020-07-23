/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.controller   
 * @author: Frankjiu
 * @date: 2020年4月24日
 * @version: V1.0
 */

package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.User;
import com.service.StoreService;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: Store Controller
 * @author: Frankjiu
 * @date: 2020年4月24日
 */
@RestController
@Slf4j
public class StoreController {

	@Autowired
	StoreService storeService;

	@GetMapping("/storeConsumer")
	public String storeConsumer() {
		log.info("calling storeConsumer service...");
		return storeService.storeService();
	}

	@GetMapping("/storeConsumerFallBackTest")
	public String storeConsumerFallBackTest() {
		log.info("calling storeConsumerFallBackTest service...");
		return storeService.storeConsumerFallBackTest();
	}

	@GetMapping("/speakConsumer")
	public String speakConsumer() {

		String separator = System.getProperty("line.separator");

		StringBuilder stb = new StringBuilder();
		stb.append(storeService.storeService()).append(separator);
		stb.append(storeService.hello("frank1")).append("\n");
		stb.append(storeService.hello("frank2", 30)).append("\n");
		stb.append(storeService.hello(new User("frank", 30))).append("\n");
		return stb.toString();
	}

}
