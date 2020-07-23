package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.entity.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public User findByName(String name) {
		return this.userDao.findByUsername(name);
	}

	public List<User> findAll() {
		return userDao.findAll();
	}
}
