/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.sysconfig   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月2日下午4:30:05
 * @version V1.0
 */

package com.modules.init;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 系统任务1
 * 
 * @author: Frankjiu
 * @date: 2020年4月2日 下午4:30:05
 */
@Component
@Order(1)
public class MyCommandLineRunner1 implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * @param args
	 * @throws Exception
	 */
	@Override
	public void run(String... args) throws Exception {
		logger.info("Runner1>>>>>>>>>>>>>>>>>>>>>{}", Arrays.toString(args));
	}

}
