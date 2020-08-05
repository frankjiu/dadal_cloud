package com.resp;

public enum EResponseCode {

	/**
	 * 成功 00000
	 */
	SUCCESS("00000", "success"),

	/**
	 * 失败 11111
	 */
	FAIL("11111", "fail"),

	/**
	 * 签名验证失败 90000
	 */
	SIGN_ERROR("90000", "Signature verification failed"),

	/**
	 * 系统错误 90001
	 */
	SYSTEM_ERROR("90001", "Internal System Error"),

	/**
	 * 参数验证失败 90002
	 */
	PARAM_ERROR("90002", "Parameter verification failed"),

	/**
	 * 认证失败 90003
	 */
	AUTHORIZATION_ERROR("90003", "Login timed out! please login again"),

	/**
	 * 请求方法不支持 90004
	 */
	METHOD_NOT_ALLOWED("90004", "Request method is not supported"),

	/**
	 * TOKEN验证错误 90005
	 */
	TOKEN_ERROR("90005", "TOKEN verification error"),

	/**
	 * 找不到资源 90006
	 */
	RESOURCE_NOT_FOUND("90006", "The specified resource could not be found"),

	/**
	 * 不允许访问该查询条件
	 */
	CONDITION_ACCESS_DENIED("90007", "Refuse to access the query condition"),

	/**
	 * 数量超出限制
	 */
	NUMS_OUT_OF_SIZE("90008", "Query number exceeds limit"),

	/**
	 * sso系统登录失败
	 */
	SSO_LOGIN_ERROR("90009", "Sso Login Error"),

	/**
	 * 用户仅注册
	 */
	USER_JUST_REGISTED("90010", "User Just Registed");

	private String code;
	private String describe;

	EResponseCode(String value, String describe) {
		this.code = value;
		this.describe = describe;
	}

	public String getCode() {
		return code;
	}

	public String getDescribe() {
		return describe;
	}
}
