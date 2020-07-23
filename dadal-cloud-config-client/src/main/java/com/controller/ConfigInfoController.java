/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.controller   
 * @author: Frankjiu
 * @date: 2020年4月29日
 * @version: V1.0
 */

package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: Config Info Controller
 * @author: Frankjiu
 * @date: 2020年4月29日
 */
@RestController
@RefreshScope
public class ConfigInfoController {

	@Autowired
	private Environment env;

	@GetMapping("/from")
	public String from() {
		String prop = env.getProperty("from", "undefined");
		String separator = System.getProperty("line.separator");
		return "[from Environment]: " + prop + "======" + separator;
	}

}
