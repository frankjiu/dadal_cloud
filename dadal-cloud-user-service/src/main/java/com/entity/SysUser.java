package com.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

/**
 * 用户实体类
 * 
 * @author: Frankjiu
 * @date: 2018年4月6日 下午8:00:49
 */
@Data
@Entity
@Table(name = "SYS_USER")
public class SysUser implements Serializable {

	private static final long serialVersionUID = 1L;

	// 主键
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "ID", nullable = false, unique = true)
	private String id;

	// 姓名
	@Column(name = "USER_NAME")
	private String userName;

	// 账号
	@Column(name = "LOGIN_NAME")
	private String loginName;

	// 密码
	@Column(name = "PASS_WORD")
	private String passWord;

	// 手机号码
	@Column(name = "MOBILE_PHONE")
	private String mobilePhone;

	// 微信号
	@Column(name = "WECHAT")
	private String wechat;

	// 身份证号
	@Column(name = "ID_CARD")
	private String idCard;

	// 电子邮箱
	@Column(name = "EMAIL")
	private String email;

	// 用户角色类型 0:管理员/1:专家/2:普通/3:黑名单
	@Column(name = "TYPE")
	private Integer type;

	// 是否冻结 0:否/1:是
	@Column(name = "IS_FREEZE")
	private Integer isFreeze;

	// 专业学科ID
	@Column(name = "SUBJECT_ID")
	private String subjectId;

	// 是否已修改过学科
	@Column(name = "HAS_UPDATED")
	private boolean hasUpdated;

	// 用户积分
	@Column(name = "POINTS")
	private Integer points;

	// 用户级别称谓,根据积分判断
	@Column(name = "GRADE")
	private String grade;

	// 金币奖励系数 普通用户:1/专家用户:2
	@Column(name = "COIN_RATIO")
	private String coinRatio;

	// 最近登录时间
	@Column(name = "LOGIN_TIME")
	private Date loginTime;

	// 最近退出时间
	@Column(name = "LOGOUT_TIME")
	private Date logoutTime;

	// 创建时间
	@Column(name = "CREATE_TIME")
	// @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	// @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	// 起始创建时间
	@Transient
	private Date createTimeBefore;

	// 结束创建时间
	@Transient
	private Date createTimeAfter;

	// 更新时间
	@Column(name = "UPDATE_TIME")
	private Date updateTime;

	// 验证码
	@Transient
	private String code;

	// 角色ID
	@Transient
	private String roleId;

}
