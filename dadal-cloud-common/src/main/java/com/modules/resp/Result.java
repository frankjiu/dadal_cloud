/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.modules.resp   
 * @author: Frankjiu
 * @date: 2020年7月21日
 * @version: V1.0
 */

package com.modules.resp;

import java.util.HashMap;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: TODO
 * @author: Frankjiu
 * @date: 2020年4月03日
 */
@Data
@ApiModel(value = "Global Response Result")
public class Result {

	@ApiModelProperty(value = "是否成功")
	private Boolean success;

	@ApiModelProperty(value = "返回码")
	private Integer code;

	@ApiModelProperty(value = "返回消息")
	private String message;

	@ApiModelProperty(value = "返回数据")
	private Map<String, Object> data = new HashMap<String, Object>();

	private Result() {
	}

	public static Result suc() {
		Result Result = new Result();
		Result.setSuccess(ResultCode.SUC.getSuccess());
		Result.setCode(ResultCode.SUC.getCode());
		Result.setMessage(ResultCode.SUC.getMessage());
		return Result;
	}

	public static Result fail() {
		Result Result = new Result();
		Result.setSuccess(ResultCode.FAIL.getSuccess());
		Result.setCode(ResultCode.FAIL.getCode());
		Result.setMessage(ResultCode.FAIL.getMessage());
		return Result;
	}

	public static Result err() {
		Result Result = new Result();
		Result.setSuccess(ResultCode.UNKNOW_ERROR.getSuccess());
		Result.setCode(ResultCode.UNKNOW_ERROR.getCode());
		Result.setMessage(ResultCode.UNKNOW_ERROR.getMessage());
		return Result;
	}

	//...

	public static Result setResult(ResultCode ResultCode) {
		Result Result = new Result();
		Result.setSuccess(ResultCode.getSuccess());
		Result.setCode(ResultCode.getCode());
		Result.setMessage(ResultCode.getMessage());
		return Result;
	}

	public Result success(Boolean success) {
		this.setSuccess(success);
		return this;
	}

	public Result message(String message) {
		this.setMessage(message);
		return this;
	}

	public Result code(Integer code) {
		this.setCode(code);
		return this;
	}

	public Result data(String key, Object value) {
		this.data.put(key, value);
		return this;
	}

	public Result data(Map<String, Object> map) {
		this.setData(map);
		return this;
	}
}