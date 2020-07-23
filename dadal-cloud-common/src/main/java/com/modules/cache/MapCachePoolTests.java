/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.test.cache   
 * @author: Frankjiu
 * @date: 2020年7月16日
 * @version: V1.0
 */
package com.modules.cache;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 代码测试类MapCacheDemoTests
 * @author: Frankjiu
 * @date: 2020年7月16日
 */
public class MapCachePoolTests {

	@Autowired
	MapCachePool mapCachePool;

	@Test
	public void testCache() throws Exception {
		//public static void main(String[] args) throws InterruptedException {

		mapCachePool.add("uid_10001", "{1}", 5 * 1000);
		mapCachePool.add("uid_10002", "{2}", 6 * 1000);
		mapCachePool.add("uid_10003", "{3}", 10 * 1000);
		System.out.println("从缓存中取出值1:" + mapCachePool.get("uid_10001"));
		Thread.sleep(5000L);
		System.out.println("5秒钟过后");
		System.out.println("从缓存中取出值1:" + mapCachePool.get("uid_10001"));
		System.out.println("从缓存中取出值2:" + mapCachePool.get("uid_10002"));
		System.out.println("从缓存中取出值3:" + mapCachePool.get("uid_10003"));
		Thread.sleep(5000L);
		System.out.println("从缓存中取出值3:" + mapCachePool.get("uid_10003"));
		// 5秒后数据自动清除了~
	}
}