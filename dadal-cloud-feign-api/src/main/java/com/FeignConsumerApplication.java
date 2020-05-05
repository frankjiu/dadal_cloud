/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com   
 * @author: Frankjiu
 * @date:   2020年4月18日
 * @version V1.0
 */

package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * ddl-cloud-feign-api Feign contains the Gzip Function
 * 
 * @author: Frankjiu
 * @date: 2020年4月18日
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class FeignConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignConsumerApplication.class, args);
	}

}
