/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.controller   
 * @author: Frankjiu
 * @date: 2020年5月13日
 * @version: V1.0
 */

package com.service;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: Zk ServiceMain
 * @author: Frankjiu
 * @date: 2020年5月13日
 */

/**
 * zk分布式配置中心
 * 
 * @author
 */
public class ZkServiceMain implements Watcher {

	private static final Logger logger = LoggerFactory.getLogger(ZkServiceMain.class);

	private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
	private static ZooKeeper zk = null;
	private static Stat stat = new Stat();

	//zookeeper配置数据存放路径
	private static String path = "/zkone";

	public static void zkServiceTest() throws Exception {
		//public static void main(String[] args) throws Exception {
		//连接zookeeper并且注册一个默认的监听器
		zk = new ZooKeeper("192.168.75.130:2181", 10000, new ZkServiceMain());
		//等待zk连接成功的通知
		connectedSemaphore.await();
		//获取path目录节点的配置数据，并注册默认的监听器
		logger.info(new String(zk.getData(path, true, stat)));
		Thread.sleep(Integer.MAX_VALUE);
	}

	public void process(WatchedEvent event) {
		if (KeeperState.SyncConnected == event.getState()) { //zk连接成功通知事件
			if (EventType.None == event.getType() && null == event.getPath()) {
				connectedSemaphore.countDown();
			} else if (event.getType() == EventType.NodeDataChanged) { //zk目录节点数据变化通知事件
				try {
					logger.info("配置已修改为:{}", new String(zk.getData(event.getPath(), true, stat)));
				} catch (Exception e) {
					logger.info(e.getMessage(), e);
				}
			}
		}
	}
}
