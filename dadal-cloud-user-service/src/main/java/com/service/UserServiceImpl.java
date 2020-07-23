/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.service   
 * @author: Frankjiu
 * @date: 2020年6月12日
 * @version: V1.0
 */

package com.service;

import java.sql.SQLException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserMapper;
import com.entity.User;

/**
 * @Description: UserServiceImpl
 * @author: Frankjiu
 * @date: 2020年6月12日
 */

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = SQLException.class)
	public User queryUserByUsername(String username) {
		return userMapper.queryUserByUsername(username);
	}

	@Override
	public Integer insertUser(User user) {
		// 加密
		String salt = UUID.randomUUID().toString();
		String s = "";//new Sha256Hash(user.getPassword(), salt, MyConstant.INTERCOUNT).toBase64();
		// 设置密文
		user.setPassword(s);
		// 设置盐
		user.setSalt(salt);
		return userMapper.insertUser(user);
	}
}