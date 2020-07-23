/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.mongodao   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月3日下午6:42:47
 * @version V1.0
 */

package com.modules.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.modules.swagger2.entity.User;

/**
 * mongodb接口层
 * 
 * @author: Frankjiu
 * @date: 2020年4月3日 下午6:42:47
 */

public interface UserDao extends MongoRepository<User, Integer> {

	List<User> findByAge(Integer age);

	User findByMobileEquals(String mobile);

}
