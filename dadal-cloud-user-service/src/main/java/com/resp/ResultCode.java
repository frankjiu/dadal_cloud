package com.resp;

import lombok.Getter;

/**
 * Result Code
 */
@Getter
public enum ResultCode {

	SUC(true, 200, "SUCCESS"), //成功
	FAIL(false, 400, "FAIL"), //失败
	FAIL_PARAM(false, 401, "FAIL_PARAM"), //参数错误
	FAIL_PARSE(false, 202, "FAIL_PARSE"), //解析错误
	NOT_FOUND(false, 404, "NOT_FOUND"), //不存在
	INTERNAL_SERVER_ERROR(false, 500, "INTERNAL_SERVER_ERROR"), //服务器错误
	UNKNOW_ERROR(false, 501, "UNKNOW_ERROR");//未知错误

	private Boolean success;
	private Integer code;
	private String message;

	private ResultCode(Boolean success, Integer code, String message) {
		this.success = success;
		this.code = code;
		this.message = message;
	}

}
