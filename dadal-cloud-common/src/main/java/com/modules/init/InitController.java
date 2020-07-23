/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package cn.com.Controller   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年3月31日下午9:40:08
 * @version V1.0
 */

package com.modules.init;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.modules.files.entity.Config;
import com.utils.StringUtils;

/**
 * 测试读取配置信息
 * 
 * @author: Frankjiu
 * @date: 2020年3月31日 下午9:40:08
 */
@RestController
public class InitController {

	@Autowired
	Config config;

	@GetMapping("/init")
	public String init() {
		return config.toString();
	}

	@GetMapping("/test")
	public ModelAndView test() {
		List<Config> configs = new ArrayList<>();
		Config config1 = new Config();
		config1.setIp("192.168.1.156");
		config1.setPort(9001);
		Config config2 = new Config();
		config2.setIp("192.168.1.157");
		config2.setPort(9002);
		Config config3 = new Config();
		config3.setIp("192.168.1.158");
		config3.setPort(9003);
		configs.add(config1);
		configs.add(config2);
		configs.add(config3);
		ModelAndView mv = new ModelAndView();
		mv.addObject("configs", configs);
		mv.setViewName("configs");
		return mv;
	}

	@GetMapping("/config")
	public Config config() {
		Config testConfig = new Config();
		testConfig.setIp("192.168.1.109");
		testConfig.setPort(9999);
		testConfig.setCreateTime(new Date());
		testConfig.setUpdateTime(new Date());
		return testConfig;
	}

	private final Logger logger = LoggerFactory.getLogger(InitController.class);

	@Autowired
	private DiscoveryClient client;

	@Autowired
	private Registration registration;

	@GetMapping("/storeService")
	public String storeService() {
		List<ServiceInstance> services = client.getInstances(registration.getServiceId());
		if (StringUtils.isNotEmpty(services)) {
			for (ServiceInstance service : services) {
				logger.info("EEEEEEEEEEE Get service info from the regist-center >>>>>> host:{}, serviceId:{}", service.getHost(),
						service.getServiceId());
			}
		}
		return "Now the service(spring.application.name=ddl-cloud-store-service) hash been injected into Eureka-Register!";
	}

}
