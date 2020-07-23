/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月14日下午4:56:20
 * @version V1.0
 */

package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

/**
 * @author: Frankjiu
 * @date: 2020年4月14日 下午4:56:20
 */
@SpringBootApplication
@EnableAdminServer
public class AdminServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminServerApplication.class, args);
	}

}
