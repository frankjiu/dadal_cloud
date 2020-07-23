/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.sysconfig   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月2日下午5:01:28
 * @version V1.0
 */

package com.modules.init;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: Frankjiu
 * @date: 2020年4月2日 下午5:01:28
 */
@WebListener
public class MyListener implements ServletRequestListener {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		logger.info(">>>>>>>>>>>>requestDestroyed...");
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		logger.info(">>>>>>>>>>>>requestInitialized...");
	}

}
