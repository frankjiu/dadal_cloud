/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.controller   
 * @author: Frankjiu
 * @date: 2020年5月14日
 * @version: V1.0
 */

package com.controller;

import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.config.ZkApi;

/**
 * @Description: Node Update Controller
 * @author: Frankjiu
 * @date: 2020年5月14日
 */
@RestController
public class NodeUpdateController {

	private static final Logger logger = LoggerFactory.getLogger(NodeUpdateController.class);

	@Autowired
	private ZkApi zkApi;

	@Value("${zookeeper.node.path}")
	private String path;

	private String newData = "55全球最新新闻2:More than 4 million new coronavirus infens and 278135 deaths worldwide";

	private static Stat stat = new Stat();

	@GetMapping("/updateNode")
	public String updateNode() {
		logger.info(">>>请求更改数据...");
		// 更新节点或节点数据,触发监听事件
		zkApi.updateNode(path, newData);
		return zkApi.getData(path, true, stat);
	}

}
