/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @author: Frankjiu
 * @date: 2020年8月27日
 * @version: V1.0
 */

package com.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description: 自定义通用异常
 * @author: Frankjiu
 * @date: 2020年8月27日
 */
@Getter
@Setter
public class CommonException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String message;
    private int code = 500;

    public CommonException() {
        super("Internal System Error");
        this.message = "Internal System Error";
    }

    public CommonException(String message) {
        super(message);
        this.message = message;
    }

    public CommonException(String message, Throwable e) {
        super(message, e);
        this.message = message;
    }

    public CommonException(String message, int code) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public CommonException(String message, int code, Throwable e) {
        super(message, e);
        this.message = message;
        this.code = code;
    }

}