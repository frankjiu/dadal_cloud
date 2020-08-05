/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com   
 * @author: Frankjiu
 * @date: 2020年5月5日
 * @version: V1.0
 */

package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: LogApplication
 * @author: Frankjiu
 * @date: 2020年5月5日
 */
@SpringCloudApplication
@ConfigurationProperties(prefix = "server")
@Slf4j
public class LogApplication {

	@Setter
	@Getter
	private int port;

	public static void main(String[] args) {
		SpringApplication.run(LogApplication.class, args);
	}

	@Bean
	public TomcatServletWebServerFactory servletContainer() {
		log.info(String.valueOf(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>当前应用端口:" + port));
		return new TomcatServletWebServerFactory(port);
	}
}
