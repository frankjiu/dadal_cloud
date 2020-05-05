/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.entity   
 * @author: Frankjiu
 * @date: 2020年4月21日
 * @version V1.0
 */

package com.entity;

import java.io.Serializable;

/**
 * @Description: 用户实体
 * @author: Frankjiu
 * @date: 2020年4月21日
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	/*主键*/
	private String id;

	/*用户名 */
	private String userName;

	/*年龄*/
	private Integer age;

	public User(String userName, Integer age) {
		super();
		this.userName = userName;
		this.age = age;
	}

	/**
	 * 获取 id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取 userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置 userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 获取 age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * 设置 age
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

}
