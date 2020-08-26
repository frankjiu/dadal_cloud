/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.config   
 * @author: Frankjiu
 * @date: 2020年8月26日
 * @version: V1.0
 */

package com.config;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 全局异常处理器
 * @author: Frankjiu
 * @date: 2020年8月26日
 */
@RestController
@ControllerAdvice
public class AdviceController {
    @Autowired
    public HttpServletResponse httpServletResponse;

    /**
     * shiro权限错误
     */
    @ExceptionHandler(AuthorizationException.class)
    @CrossOrigin
    public String authorizationException(AuthorizationException e) {
        if (e instanceof UnauthenticatedException) {
            return "token错误或未登录";
        } else if (e instanceof UnauthorizedException) {
            return "用户无权限";
        } else {
            return e.getMessage();
        }
    }
}