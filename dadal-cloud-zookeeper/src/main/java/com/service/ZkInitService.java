/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.config   
 * @author: Frankjiu
 * @date: 2020年5月13日
 * @version: V1.0
 */

package com.service;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.config.WatcherApi;
import com.config.ZkApi;

/**
 * @Description: ZkService
 * @author: Frankjiu
 * @date: 2020年5月13日
 */
@Service
public class ZkInitService {

	private static final Logger logger = LoggerFactory.getLogger(ZkInitService.class);

	@Autowired
	private ZkApi zkApi;

	@Value("${zookeeper.node.path}")
	private String path;

	@PostConstruct
	public void init() {
		logger.info(">>>>>>初始化ZK节点数据......");
		/**
		 * zkApi.createNode(path, "global news 3");
		 */
		String value = zkApi.getData(path, new WatcherApi());
		logger.info("获取ZK目标节点数据:{}", value);
	}

}
