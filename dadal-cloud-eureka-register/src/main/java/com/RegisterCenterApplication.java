package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Register Center Application
 * 
 * @author: Frankjiu
 * @date: 2020年3月31日 下午9:36:16
 */
@SpringBootApplication
@EnableEurekaServer
public class RegisterCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegisterCenterApplication.class, args);
	}

}
