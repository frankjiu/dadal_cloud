/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.service   
 * @author: Frankjiu
 * @date: 2020年6月12日
 * @version: V1.0
 */

package com.service;

import com.entity.User;

/**
 * @Description: TODO
 * @author: Frankjiu
 * @date: 2020年6月12日
 */

public interface UserService {

	User queryUserByUsername(String username);

	Integer insertUser(User user);

}
