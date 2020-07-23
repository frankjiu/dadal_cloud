/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.test.cache   
 * @author: Frankjiu
 * @date: 2020年7月16日
 * @version: V1.0
 */

package com.modules.cache;

import java.lang.ref.SoftReference;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import lombok.AllArgsConstructor;
import lombok.Data;

//Java Caching定义了5个核心接口，分别是CachingProvider, CacheManager, Cache, Entry 和 Expiry。
//CachingProvider定义了创建、配置、获取、管理和控制多个CacheManager。一个应用可以在运行期访问多个CachingProvider。
//CacheManager定义了创建、配置、获取、管理和控制多个唯一命名的Cache，这些Cache存在于- CacheManager的上下文中。一个CacheManager仅被一个CachingProvider所拥有。
//Cache是一个类似Map的数据结构并临时存储以Key为索引的值。一个Cache仅被一个CacheManager所拥有。
//Entry是一个存储在Cache中的key-value对。
//每一个存储在Cache中的条目有一个定义的有效期，即Expiry Duration。
//一旦超过这个时间，条目为过期的状态。一旦过期，条目将不可访问、更新和删除。缓存有效期可以通过ExpiryPolicy设置。

/**
 * 在集群环境下，常用的分布式缓存有Redis、Memcached等。 但在某些业务场景上, 可能不需要去搭建一套复杂的分布式缓存系统,
 * 而使用内存缓存(map简单缓存)
 */
public class MapCachePool {

	private static final int SCAN_PERIOD_SECONDS = 5;

	private static final ConcurrentHashMap<String, SoftReference<CacheObject>> cache = new ConcurrentHashMap<>();

	public MapCachePool() {
		// 通过建立守护线程每隔5秒扫描清理过期对象.
		Thread cleanerThread = new Thread(() -> {
			while (!Thread.currentThread().isInterrupted()) {
				try {
					Thread.sleep(SCAN_PERIOD_SECONDS * 1000);
					cache.entrySet().removeIf(entry -> Optional.ofNullable(entry.getValue()).map(SoftReference::get)
							.map(CacheObject::isExpired).orElse(false));
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		});
		cleanerThread.setDaemon(true);
		cleanerThread.start();
	}

	public void add(String key, Object value, long periodInMillis) {
		if (key == null) {
			return;
		}
		if (value == null) {
			cache.remove(key);
		} else {
			long expiryTime = System.currentTimeMillis() + periodInMillis;
			cache.put(key, new SoftReference<>(new CacheObject(value, expiryTime)));
		}
	}

	public void remove(String key) {
		cache.remove(key);
	}

	public Object get(String key) {
		return Optional.ofNullable(cache.get(key)).map(SoftReference::get).filter(cacheObject -> !cacheObject.isExpired())
				.map(CacheObject::getValue).orElse(null);
	}

	public void clear() {
		cache.clear();
	}

	public long size() {
		return cache.entrySet().stream().filter(entry -> Optional.ofNullable(entry.getValue()).map(SoftReference::get)
				.map(cacheObject -> !cacheObject.isExpired()).orElse(false)).count();
	}

	/**
	 * 缓存对象value值
	 */
	@Data
	@AllArgsConstructor
	private static class CacheObject {
		private Object value;
		private long expiryTime;

		boolean isExpired() {
			return System.currentTimeMillis() > expiryTime;
		}
	}
}
