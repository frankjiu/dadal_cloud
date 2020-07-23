/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.controller   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月1日下午10:21:32
 * @version V1.0
 */

package com.modules.exception;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

/**
 * File UploadException Handler
 * 
 * @author: Frankjiu
 * @date: 2020年4月1日 下午10:21:32
 */
@ControllerAdvice
public class UploadExceptionHandler {

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ModelAndView uploadException(MaxUploadSizeExceededException e, HttpServletResponse resp) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", "上传文件大小超限!");
		mv.setViewName("error");
		return mv;
	}

}
