/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.controller   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月2日下午6:09:28
 * @version V1.0
 */

package com.modules.swagger2.controller;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modules.swagger2.entity.User;
import com.modules.swagger2.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 用户控制层
 * 
 * @author: Frankjiu
 * @date: 2020年4月2日 下午6:09:28
 */
@RestController
@Api(tags = "用户控制层接口")
public class SwUserController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserService userService;

	@SuppressWarnings("rawtypes")
	@Autowired
	RedisTemplate redisTemplate;

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@ApiOperation(value = "添加用户", notes = "传递参数值包括:用户名,密码,年龄")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "userName", value = "用户名", required = true, defaultValue = "frank"),
			@ApiImplicitParam(paramType = "query", name = "password", value = "密码", required = true, defaultValue = "123456"),
			@ApiImplicitParam(paramType = "query", name = "age", value = "年龄", required = false) })
	@GetMapping("/addUser")
	public int addUser(@Validated User user) {
		user.setUserName("test111");
		user.setPassWord("121121");
		user.setAge(300);

		int i = 0;
		try {
			i = SecureRandom.getInstanceStrong().nextInt(999999999 - 366553336 + 1) + 366553336;
		} catch (NoSuchAlgorithmException e) {
			logger.info("随机数生成异常:{}", e.getMessage(), e);
		}
		user.setMobile("13".concat(String.valueOf(i)));
		user.setCreateTime(new Date());

		ValueOperations op1 = redisTemplate.opsForValue();
		op1.set("cacheUser", user);
		User u = (User) op1.get("cacheUser");
		logger.info("redis缓存的用户:{}", u);

		ValueOperations<String, String> op2 = stringRedisTemplate.opsForValue();
		op2.set("qq", "2309095678");
		String qq = op2.get("qq");
		logger.info("redis缓存的QQ号:{}", qq);

		return userService.addUser(user);
	}

	@ApiResponses({ @ApiResponse(code = 200, message = "删除成功!"), @ApiResponse(code = 500, message = "删除失败!") })
	@ApiOperation(value = "删除用户", notes = "通过手机号删除用户")
	@DeleteMapping("/deleteUserById")
	public int deleteUserById(String id) {
		return userService.deleteUserById(id);
	}

	@PutMapping("/updateUserById")
	public int updateUserById(User user) {
		return userService.updateUserById(user);
	}

	@GetMapping("/getUserById")
	public User getUserById(String id) {
		return userService.getUserById(id);
	}

	@GetMapping("/getAllUsers")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@ApiIgnore
	@GetMapping("/getUsersByCreateTimeAndAges")
	public List<User> getUsersByCreateTimeAndAges(String startCreateTime, String endCreateTime, Integer[] ages) {
		startCreateTime = "2020-04-02 00:00:00";
		endCreateTime = "2020-04-02 23:59:59";
		ages = new Integer[] { 25, 27 };
		return userService.getUsersByCreateTimeAndAges(startCreateTime, endCreateTime, ages);
	}

	/**
	 * paramType:
	 * query: @RequestParam/path: @PathVariable/header: @RequestHeader/body/form
	 * 
	 * @param mobile
	 * @return User
	 */
	@ApiOperation(value = "查询用户", notes = "根据手机号查询用户")
	@ApiImplicitParam(paramType = "path", name = "mobile", value = "用户mobile", required = true)
	@GetMapping("/getUserByMobile")
	public User getUserByMobile(String mobile) {
		mobile = "19955556623";
		return userService.getUserByMobile(mobile);
	}

	@Autowired
	MongoTemplate mongoTemplate;

	@GetMapping("/mongoData")
	public List<User> mongoData() {
		List<User> userList = new ArrayList<>();

		User user1 = new User();
		user1.setId("534956728");
		user1.setUserName("MongoTest777");
		user1.setPassWord("777");
		user1.setAge(110);
		user1.setMobile("17799598382");
		user1.setCreateTime(new Date());
		userList.add(user1);

		User user2 = new User();
		user2.setId("534956729");
		user2.setUserName("MongoTest888");
		user2.setPassWord("888");
		user2.setAge(110);
		user2.setMobile("18899598954");
		user2.setCreateTime(new Date());
		userList.add(user2);

		mongoTemplate.insertAll(userList);
		List<User> allList = mongoTemplate.findAll(User.class);
		logger.info("mongoDb数据:{}", allList);
		List<User> list = mongoTemplate.find(new Query(Criteria.where("age").is(110)), User.class);
		logger.info("当前条件下mongoDb数据:{}", list);
		User uniqueUser = mongoTemplate.findOne(new Query(Criteria.where("mobile").is("18899598954")), User.class);
		logger.info("当前唯一条件下mongoDb数据:{}", uniqueUser);
		return allList;

	}

	/**
	 * redis独立服务器 session共享配置
	 */
	@Value("${server.port}")
	String port;

	@PostMapping("/save")
	// 本地使用浏览器发送请求测试使用 @GetMapping("/save")
	public String saveName(String name, HttpSession redisSession) {
		name = "frank";
		redisSession.setAttribute("name", name);
		return port;
	}

	@GetMapping("/get")
	public String getName(HttpSession redisSession) {
		return port + ":" + redisSession.getAttribute("name").toString();
	}

}
