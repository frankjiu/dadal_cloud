/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.config   
 * @author: Frankjiu
 * @date: 2020年7月16日
 * @version: V1.0
 */

package com.modules.cache.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.modules.cache.MapCachePool;

/**
 * @Description: TODO
 * @author: Frankjiu
 * @date: 2020年7月16日
 */
@Configuration
public class configs {

	@Bean
	public MapCachePool mapCachePool() {
		return new MapCachePool();
	}
}
