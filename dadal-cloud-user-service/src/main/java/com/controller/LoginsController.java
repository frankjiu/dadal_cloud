/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.controller   
 * @author: Frankjiu
 * @date: 2020年6月12日
 * @version: V1.0
 */

package com.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.User;
import com.service.UserService;

/**
 * @Description: LoginController
 * @author: Frankjiu
 * @date: 2020年6月12日
 */
@Controller
@RequestMapping("/user")
public class LoginsController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public String loginLogic(User user) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
		// 登录失败会抛出异常，则交由异常解析器处理
		token.setRememberMe(true);
		subject.login(token);

		return "main";
	}

	@GetMapping("/register")
	public String regiter() {
		return "register";
	}

	@PostMapping("/register")
	public String logicRegiter(User user) {
		userService.insertUser(user);
		return "redirect:login";
	}
}