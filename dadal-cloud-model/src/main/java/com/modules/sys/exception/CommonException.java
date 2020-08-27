/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.modules.sys.exception   
 * @author: Frankjiu
 * @date: 2020年8月27日
 * @version: V1.0
 */

package com.modules.sys.exception;

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

    private String msg;
    private int code = 500;

    public CommonException() {
        super("Internal System Error");
        this.msg = "Internal System Error";
    }

    public CommonException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public CommonException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public CommonException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public CommonException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

}