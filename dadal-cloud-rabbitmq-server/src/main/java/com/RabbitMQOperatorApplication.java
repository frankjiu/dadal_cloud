/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com   
 * @author: Frankjiu
 * @date: 2020年4月24日
 * @version: V1.0
 */

package com;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @Description: Config Server
 * @author: Frankjiu
 * @date: 2020年4月24日
 */
@SpringCloudApplication
public class RabbitMQOperatorApplication {
	public static void main(String[] args) {
		SpringApplication.run(RabbitMQOperatorApplication.class, args);
	}
}
