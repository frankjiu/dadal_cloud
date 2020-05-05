/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.service   
 * @author: Frankjiu
 * @date: 2020年4月24日
 * @version: V1.0
 */

package com.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.User;

/**
 * @Description: ddl-cloud-store-service
 * @author: Frankjiu
 * @date: 2020年4月24日
 */
@FeignClient(value = "ddl-cloud-store-service", url = "http://localhost:2001/", fallback = StoreFallBack.class)
public interface StoreService {

	@RequestMapping("/storeService")
	public String storeService();

	@RequestMapping("/storeConsumerFallBackTest")
	public String storeConsumerFallBackTest();

	@GetMapping("/hello1")
	public String hello(@RequestParam("name") String name);

	@GetMapping("/hello2")
	public User hello(@RequestHeader("userName") String userName, @RequestHeader("age") Integer age);

	@PostMapping("/hello3")
	public String hello(@RequestBody User user);

}
