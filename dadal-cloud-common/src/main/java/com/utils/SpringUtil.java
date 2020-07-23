/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.utils   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月1日下午12:02:44
 * @version V1.0
 */

package com.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author: Frankjiu
 * @date: 2020年4月1日 下午12:02:44 通过Spring上下文获取Spring容器Bean
 */
@Component
public class SpringUtil implements ApplicationContextAware {

	private static ApplicationContext applicationContext = null;

	/**
	 * 获取applicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		if (SpringUtil.applicationContext == null) {
			SpringUtil.applicationContext = applicationContext;
		}
	}

	/**
	 * 通过name获取 Bean
	 */
	public static Object getBean(String name) {
		return getApplicationContext().getBean(name);
	}
}