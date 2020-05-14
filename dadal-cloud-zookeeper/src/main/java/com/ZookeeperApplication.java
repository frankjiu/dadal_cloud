/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com   
 * @author: Frankjiu
 * @date: 2020年5月14日
 * @version: V1.0
 */

package com;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @Description: Zookeeper Application
 * @author: Frankjiu
 * @date: 2020年5月14日
 */

@SpringCloudApplication
public class ZookeeperApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZookeeperApplication.class, args);
	}

}