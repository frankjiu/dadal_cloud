/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.controller   
 * @author: Frankjiu
 * @date: 2020年5月14日
 * @version: V1.0
 */

package com.controller;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.config.WatcherApi;
import com.config.ZkApi;

/**
 * @Description: UpdateHandler
 * @author: Frankjiu
 * @date: 2020年5月14日
 */
@Component
public class UpdateHandler {

	private static final Logger logger = LoggerFactory.getLogger(UpdateHandler.class);

	@Autowired
	private ZkApi zkApi;

	@Value("${zookeeper.node.path}")
	private String path;

	/**
	 * @param event
	 * @see org.apache.zookeeper.Watcher#process(org.apache.zookeeper.WatchedEvent)
	 */
	public void handler(WatchedEvent event) {
		logger.info(">>>进入监听...");
		if (event.getType() == EventType.NodeDataChanged) { //zk目录节点数据变化通知事件
			try {
				logger.info(">>>监听到节点配置信息已修改:{}", zkApi.getData(path, new WatcherApi()));
			} catch (Exception e) {
				logger.info(e.getMessage(), e);
			}
		}

	}

}
