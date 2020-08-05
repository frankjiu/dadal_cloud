package com.resp;

import java.util.Collection;

import lombok.Data;

/**
 * 返回数据
 *
 * @author chenshun
 */
@Data
public class Result<T> {
	private String code;
	private String message;
	private boolean success;
	private T data;

	public Result() {
	}

	public Result(EResponseCode e) {
		this.code = e.getCode();
		this.message = e.getDescribe();
	}

	public Result(EResponseCode e, boolean success) {
		this.code = e.getCode();
		this.message = e.getDescribe();
		this.success = success;
	}

	public Result(EResponseCode e, String message) {
		this.code = e.getCode();
		this.message = message;
	}

	public Result(EResponseCode e, String message, boolean success) {
		this.code = e.getCode();
		this.message = message;
		this.success = success;
	}

	public Result(EResponseCode e, T data) {
		this.success = true;
		this.code = e.getCode();
		this.data = data;
	}

	/**
	 * 根据状态返回一个Result
	 * 
	 * @param e 状态码
	 * @return 根据这个状态码生成的result
	 */
	public static Result status(EResponseCode e) {
		return new Result(e);
	}

	/**
	 * 返回成功,并且带上一条消息
	 * 
	 * @param message 需要带回的消息
	 * @return 根据消息生成的result you may setup docker deploment run configuration
	 *         for the following files
	 */
	public static Result success(String message) {

		return new Result(EResponseCode.SUCCESS, message, true);
	}

	/**
	 * 返回成功
	 * 
	 * @return 表示成功的result
	 */
	public static Result success() {
		Result result = new Result();
		result.setSuccess(true);
		result.setCode(EResponseCode.SUCCESS.getCode());
		return result;
	}

	/**
	 * 返回成功,并且带回所需要的数据
	 *
	 * @param data 需要带回的数据
	 * @return 成功, 并且带有返回数据的result
	 */
	public static <T> Result<T> success(T data) {
		return new Result<>(EResponseCode.SUCCESS, data);
	}

	/**
	 * 返回成功,并且带回分页所需要的数据,需要指明总记录数
	 * 
	 * @param list 本次分页所包含的数据
	 * @param total 总记录数
	 * @return 成功,并且带有分页信息的result
	 */
	@SuppressWarnings("unchecked")
	public static <T> Result success(Collection<T> list, Number total) {
		PageList pageList = new PageList();
		pageList.setData(list);
		pageList.setTotalNum(total.longValue());
		return success(pageList);
	}

	/**
	 * 返回失败
	 * 
	 * @return 表示失败的result
	 */
	public static Result fail() {
		return new Result(EResponseCode.FAIL);
	}

	public static Result fail(Exception e) {
		return new Result(EResponseCode.FAIL, e.getMessage());
	}

	/**
	 * 返回失败,并且带上错误信息,默认的错误码是"1111"
	 * 
	 * @param message 失败所包含的信息
	 * @return 失败,并且含有错误信息的result
	 */
	public static Result fail(String message) {
		return new Result(EResponseCode.FAIL, message);
	}

	/**
	 * 返回失败,并且指定错误信息和错误码
	 * 
	 * @param e 错误码
	 * @return 失败,并且包含指定错误信息和错误码的result
	 */
	public static Result fail(EResponseCode e) {
		return new Result(e);
	}

	/**
	 * 返回失败,并且指定错误信息和错误码
	 * 
	 * @param e 错误码
	 * @param message 需要返回的消息
	 * @return 失败,并且包含指定错误信息和错误码的result
	 */
	public static Result fail(EResponseCode e, String message) {
		return new Result(e, message);
	}

}
