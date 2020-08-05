package com.resp;

import lombok.Getter;
import lombok.Setter;

/**
 * 自定义异常
 */
@Getter
@Setter
public class DEFException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String msg;
	private int code = 500;

	public DEFException(String msg) {
		super(msg);
		this.msg = msg;
	}

	public DEFException() {
		super("Internal System Error");
		this.msg = "Internal System Error";
	}

	public DEFException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}

	public DEFException(String msg, int code) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}

	public DEFException(String msg, int code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}

}
