/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.service   
 * @author: Frankjiu
 * @date: 2020年4月22日
 * @version: V1.0
 */

package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * consumer service
 * 
 * @author: Frankjiu
 * @date: 2020年4月22日
 */
@Service
public class ConsumerService {

	@Autowired
	RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "storeFallBack")
	public String storeConsumer() {
		return restTemplate.getForEntity("http://ddl-cloud-store-service/storeService", String.class).getBody();
	}

	public String storeFallBack() {
		return "ddl-cloud-store-service error";
	}

	public String add(User user) {
		user = new User("frank", 30);
		return restTemplate.postForEntity("http://ddl-cloud-store-service/user", user, String.class).getBody();
	}

	public void put(User user, String id) {
		user = new User("frank", 30);
		restTemplate.put("http://ddl-cloud-store-service/user/{1}", user, id);
	}

	public void delete(String id) {
		restTemplate.delete("http://ddl-cloud-store-service/user/{1}", id);
	}

}
