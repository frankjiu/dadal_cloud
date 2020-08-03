/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.entity   
 * @author: Frankjiu
 * @date: 2020年8月3日
 * @version: V1.0
 */

package com.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedBy;

import lombok.Data;

/**
 * @Description: TODO
 * @author: Frankjiu
 * @date: 2020年8月3日
 */
@Data
public class UserDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/*** 用户ID */
	@NotNull(message = "用户id不能为空", groups = Update.class)
	private Long userId;

	/**
	 * 用户名
	 */
	@NotBlank(message = "用户名不能为空")
	@Length(max = 20, message = "用户名不能超过20个字符", groups = { CreatedBy.class, Update.class })
	@Pattern(regexp = "^[0-9*]*$", message = "用户昵称限制：最多20字符，包含文字、字母和数字")
	private String username;

	/**
	 * 手机号
	 */
	@NotBlank(message = "手机号不能为空")
	@Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误", groups = { Insert.class, Update.class })
	private String mobile;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 邮箱
	 */
	@NotBlank(message = "联系邮箱不能为空")
	@Email(message = "邮箱格式不对")
	private String email;

	/**
	 * 密码
	 */
	private String password;

	/*** 创建时间 */
	@Future(message = "时间必须是将来时间", groups = { Insert.class })
	private Date createTime;

}