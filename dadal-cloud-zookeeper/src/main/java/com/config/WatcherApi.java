/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.config   
 * @author: Frankjiu
 * @date: 2020年5月13日
 * @version: V1.0
 */

package com.config;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Description: Watcher Api
 * @author: Frankjiu
 * @date: 2020年5月13日
 */
@Component
public class WatcherApi implements Watcher {

	private static final Logger logger = LoggerFactory.getLogger(WatcherApi.class);

	@Override
	public void process(WatchedEvent event) {
		logger.info("Watcher listener event:{}", event.getState());
		logger.info("Watcher listener path:{}", event.getPath());
		// Three listener type: create, update, delete
		logger.info("Watcher listener type:{}", event.getType());

		if (event.getType() == EventType.NodeDataChanged) { //zk目录节点数据变化通知事件
			logger.info("...handler service begin...");
		}

	}

}
