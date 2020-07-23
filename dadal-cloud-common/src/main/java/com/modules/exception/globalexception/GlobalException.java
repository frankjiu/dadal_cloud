package com.modules.exception.globalexception;

public class GlobalException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GlobalException() {
	}

	public GlobalException(String message) {
		super(message);
	}

	public GlobalException(String message, Throwable cause) {
		super(message, cause);
	}
}
