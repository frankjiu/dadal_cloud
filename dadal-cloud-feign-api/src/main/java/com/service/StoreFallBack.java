/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.service   
 * @author: Frankjiu
 * @date: 2020年5月3日
 * @version: V1.0
 */

package com.service;

import org.springframework.stereotype.Component;

import com.entity.User;

/**
 * @Description: Store FallBack
 * @author: Frankjiu
 * @date: 2020年5月3日
 */
@Component
public class StoreFallBack implements StoreService {

	/**
	 * @return
	 * @see com.service.StoreService#storeService()
	 */
	@Override
	public String storeService() {
		return "Default fallback!";
	}

	/**
	 * @return
	 * @see com.service.StoreService#storeConsumerFallBackTest()
	 */
	@Override
	public String storeConsumerFallBackTest() {
		return "Default fallback!";
	}

	/**
	 * @param name
	 * @return
	 * @see com.service.StoreService#hello(java.lang.String)
	 */
	@Override
	public String hello(String name) {
		return "Default fallback!";
	}

	/**
	 * @param userName
	 * @param age
	 * @return
	 * @see com.service.StoreService#hello(java.lang.String, java.lang.Integer)
	 */
	@Override
	public User hello(String userName, Integer age) {
		return new User("blank name", 0);
	}

	/**
	 * @param user
	 * @return
	 * @see com.service.StoreService#hello(com.entity.User)
	 */
	@Override
	public String hello(User user) {
		return "Default fallback!";
	}

}
