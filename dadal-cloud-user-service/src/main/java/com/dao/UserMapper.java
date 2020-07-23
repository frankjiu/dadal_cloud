/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.dao   
 * @author: Frankjiu
 * @date: 2020年6月12日
 * @version: V1.0
 */

package com.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.entity.User;

/**
 * @Description: UserMapper
 * @author: Frankjiu
 * @date: 2020年6月12日
 */

@Mapper
public interface UserMapper {
	User queryUserByUsername(@Param("username") String username);

	Integer insertUser(User user);

	/**
	 * MyBatis脚本文件方式(当多参数时不需要写parameterType)
	 */
	/*SysUser findUsersByWeChatsAndPoint(@Param("minCreateTime") String minCreateTime, @Param("maxCreateTime") String maxCreateTime,
			@Param("point") Integer thePoint, @Param("weChats") String[] theWeChats);*/

	/**
	 * MyBatis全注解方式
	 */
	/*@Select("SELECT * FROM qa_user WHERE userName = #{userName}")
	SysUser findUserByName(@Param("userName") String userName);*/

	/**
	 * MyBatis全注解方式
	 */
	/*@Delete("DELETE from qa_user WHERE id = #{id}")
	void deleteUser(@Param("id") int id);*/

}