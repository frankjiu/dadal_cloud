/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.controller   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月2日上午4:24:30
 * @version V1.0
 */

package com.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: Frankjiu
 * @date: 2020年4月2日 上午4:24:30
 */
@Controller
public class LoginController {

	@GetMapping("/doLogin")
	// @PostMapping("/doLogin")
	public String doLogin(String username, String password, Model model) {
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
		} catch (Exception e) {
			model.addAttribute("error", "用户名或密码错误!");
			return "login";
		}
		return "redirect:/index";
	}

	@RequiresRoles("admin")
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}

	@RequiresRoles(value = { "admin", "user" }, logical = Logical.OR)
	@GetMapping("/user")
	public String user() {
		return "user";
	}

}
