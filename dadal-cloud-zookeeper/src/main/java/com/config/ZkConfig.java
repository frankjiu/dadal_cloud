/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.config   
 * @author: Frankjiu
 * @date: 2020年5月13日
 * @version: V1.0
 */

package com.config;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.controller.UpdateHandler;

/**
 * @Description: Zookeeper Config
 * @author: Frankjiu
 * @date: 2020年5月13日
 */

@Configuration
public class ZkConfig {
	private static final Logger logger = LoggerFactory.getLogger(ZkConfig.class);

	@Value("${zookeeper.url}")
	private String url;

	@Value("${zookeeper.timeout}")
	private int timeout;

	@Autowired
	UpdateHandler updateHandler;

	@Bean(name = "zkClient")
	public ZooKeeper zkClient() {
		ZooKeeper zooKeeper = null;
		try {
			final CountDownLatch countDownLatch = new CountDownLatch(1);
			//连接成功后,会回调watcher监听,此连接操作是异步的,执行完new语句后,直接调用后续代码
			//  可指定多台服务地址 127.0.0.1:2181, 127.0.0.1:2182, 127.0.0.1:2183
			zooKeeper = new ZooKeeper(url, timeout, new Watcher() {
				@Override
				public void process(WatchedEvent event) {
					if (Event.KeeperState.SyncConnected == event.getState()) {
						//如果收到了服务端的响应事件,连接成功
						countDownLatch.countDown();
						// 处理业务
						handler(event);
					}
				}
			});
			countDownLatch.await();
			logger.info(">>>ZK初始化连接状态:{}", zooKeeper.getState());
		} catch (Exception e) {
			logger.error(">>>ZK初始化连接异常:{}", e);
		}
		return zooKeeper;
	}

	protected void handler(WatchedEvent event) {
		updateHandler.handler(event);
	}

}
