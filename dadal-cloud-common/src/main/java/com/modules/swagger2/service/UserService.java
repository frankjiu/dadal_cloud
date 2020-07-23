/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.dao   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月3日上午12:02:36
 * @version V1.0
 */

package com.modules.swagger2.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.modules.swagger2.entity.User;

/**
 * user service
 * 
 * @author: Frankjiu
 * @date: 2020年4月3日 上午12:02:36
 */
public interface UserService {

	int addUser(User user);

	int deleteUserById(String id);

	int updateUserById(User user);

	User getUserById(String id);

	List<User> getAllUsers();

	List<User> getUsersByCreateTimeAndAges(@Param("startCreateTime") String startCreateTime,
			@Param("endCreateTime") String endCreateTime, @Param("ages") Integer[] ages);

	// MyBatis全注解方式
	@Select("SELECT id, user_name as username, pass_word as password, age, mobile, create_time as createtime "
			+ " FROM user WHERE mobile = #{mobile}")
	User getUserByMobile(String mobile);

}
