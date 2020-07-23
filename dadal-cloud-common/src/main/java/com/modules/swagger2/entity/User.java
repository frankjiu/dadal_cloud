/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.entity   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月2日下午11:51:11
 * @version V1.0
 */

package com.modules.swagger2.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户实体
 * 
 * @author: Frankjiu
 * @date: 2020年4月2日 下午11:51:11
 */
@Data
@ApiModel(value = "用户实体类", description = "用户信息详情类")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 主键 */
	@ApiModelProperty(value = "主键")
	private String id;

	/** 用户名 */
	@ApiModelProperty(value = "用户名")
	@Size(min = 5, max = 10, message = "{user.name.size}")
	private String userName;

	/** 密码 */
	@ApiModelProperty(value = "密码")
	private String passWord;

	/** 年龄 */
	@ApiModelProperty(value = "年龄")
	@DecimalMin(value = "1", message = "{user.age.size}")
	@DecimalMax(value = "150", message = "{user.age.size}")
	private Integer age;

	/** 邮箱则使用 @Email验证 */

	/** 手机号 */
	@ApiModelProperty(value = "手机号")
	@NotNull(message = "{user.phone.notnull}")
	private String mobile;

	/** 创建时间 */
	@ApiModelProperty(value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/** 更新时间 */
	@ApiModelProperty(value = "更新时间")
	private Date updateTime;

}
