/**
 * All rights Reserved, Designed By www.xcompany.com
 * 
 * @Package: com.entity
 * @author: Frankjiu
 * @date: 2020年6月4日
 * @version: V1.0
 */
package com.entity;

import lombok.Data;

/**
 * @Description: 响应结果
 * @author: Frankjiu
 * @date: 2020年6月4日
 */
@Data
public class HttpResult {

	/** 状态码 */
	private int code;

	/** 响应体 */
	private String body;

	public HttpResult() {
		super();
	}

	public HttpResult(int code, String body) {
		super();
		this.code = code;
		this.body = body;
	}

}