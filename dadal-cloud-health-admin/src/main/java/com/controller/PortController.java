/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.controller   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月14日下午10:24:31
 * @version V1.0
 */

package com.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Frankjiu
 * @date: 2020年4月14日 下午10:24:31
 */
@RestController
public class PortController {

	@Value("${server.port}")
	String port;

	@GetMapping("/test")
	public String getPort() {
		return "localhost:" + port;
	}

}
