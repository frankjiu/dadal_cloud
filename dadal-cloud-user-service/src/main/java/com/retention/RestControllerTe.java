/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.retention   
 * @author: Frankjiu
 * @date: 2020年8月3日
 * @version: V1.0
 */

package com.retention;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.config.StringUtil;
import com.entity.User;
import com.entity.UserDto;
import com.resp.CompressUtils;
import com.resp.Result;
import com.resp.ResultOld;
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
	public void getUser(@RequestParam("userId") @NotNull(message = "用户id不能为空") Integer userId, @RequestBody @Valid UserDto param,
			HttpServletResponse resp) {

		if (StringUtil.isNotEmpty(param.getUsername()) && !checkPramDow(param.getUsername())) {
			CompressUtils.gzipResult(ResultOld.failParam("Dow is not suitable for the definition!"), resp);
		}

		User user = new User();
		user.setId(userId);

		userService.insertUser(user);

		Object data = "";

		CompressUtils.gzipResult(Result.success(data), resp);

		log.info(">>>>>>保存成功!");
	}

	private boolean checkPramDow(String param) {
		boolean length_flag = param.matches("^[1-7]{1,7}$");
		char[] charArray = param.toCharArray();
		Set<String> set = new HashSet<>(charArray.length);
		for (char c : charArray) {
			set.add(String.valueOf(c));
		}
		boolean no_repeat_flag = set.size() == charArray.length;
		return length_flag && no_repeat_flag;
	}

	@Test
	public void splitorTest() {
		String[] split = "a.b.c".split("\\.", 2); //注意转义
		for (String str : split) {
			System.out.println(str);
		}
	}

}