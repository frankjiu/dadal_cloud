/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.retention   
 * @author: Frankjiu
 * @date: 2020年8月3日
 * @version: V1.0
 */

package com.retention;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.User;
import com.entity.UserDto;
import com.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: RestFul风格注解校验
 * @author: Frankjiu
 * @date: 2020年8月3日
 */
@RestController
@Slf4j
@Validated
@RequestMapping("/users")
public class RestControllerTe {

	@Autowired
	UserService userService;

	@GetMapping("/get")
	public String getUser(@RequestParam("userId") @NotNull(message = "用户id不能为空") Integer userId, @RequestBody @Valid UserDto param) {
		User user = new User();
		user.setId(userId);
		userService.insertUser(user);
		log.info(">>>>>>保存成功!");
		return "success";
	}

}