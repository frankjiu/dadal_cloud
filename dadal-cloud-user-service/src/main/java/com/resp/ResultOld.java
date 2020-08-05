/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.modules.resp   
 * @author: Frankjiu
 * @date: 2020年7月21日
 * @version: V1.0
 */

package com.resp;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

/**
 * @Description: TODO
 * @author: Frankjiu
 * @date: 2020年4月03日
 */
@Data
public class ResultOld {

	private Boolean success;

	private Integer code;

	private String message;

	private Map<String, Object> data = new HashMap<String, Object>();

	private ResultOld() {
	}

	public static ResultOld suc(String msg) {
		ResultOld Result = new ResultOld();
		Result.setSuccess(ResultCode.SUC.getSuccess());
		Result.setCode(ResultCode.SUC.getCode());
		Result.setMessage(ResultCode.SUC.getMessage() + msg);
		return Result;
	}

	public static ResultOld failParam(String msg) {
		ResultOld Result = new ResultOld();
		Result.setSuccess(ResultCode.FAIL_PARAM.getSuccess());
		Result.setCode(ResultCode.FAIL_PARAM.getCode());
		Result.setMessage(ResultCode.FAIL_PARAM.getMessage() + msg);
		return Result;
	}

	public static ResultOld err(String msg) {
		ResultOld Result = new ResultOld();
		Result.setSuccess(ResultCode.UNKNOW_ERROR.getSuccess());
		Result.setCode(ResultCode.UNKNOW_ERROR.getCode());
		Result.setMessage(ResultCode.UNKNOW_ERROR.getMessage() + msg);
		return Result;
	}

	//...

	public static ResultOld setResult(ResultCode ResultCode) {
		ResultOld Result = new ResultOld();
		Result.setSuccess(ResultCode.getSuccess());
		Result.setCode(ResultCode.getCode());
		Result.setMessage(ResultCode.getMessage());
		return Result;
	}

	public ResultOld success(Boolean success) {
		this.setSuccess(success);
		return this;
	}

	public ResultOld message(String message) {
		this.setMessage(message);
		return this;
	}

	public ResultOld code(Integer code) {
		this.setCode(code);
		return this;
	}

	public ResultOld data(String key, Object value) {
		this.data.put(key, value);
		return this;
	}

	public ResultOld data(Map<String, Object> map) {
		this.setData(map);
		return this;
	}
}