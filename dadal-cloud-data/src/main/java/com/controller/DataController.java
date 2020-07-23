/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.controller   
 * @author: Frankjiu
 * @date: 2020年7月23日
 * @version: V1.0
 */

package com.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Device;
import com.entity.SensorData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: TODO
 * @author: Frankjiu
 * @date: 2020年4月19日
 */
@RestController
@Slf4j
public class DataController {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	@GetMapping("/mqdata")
	public String getData() {

		Map<String, Device> map = Maps.newHashMap();
		Device device = Device.builder().device_no("DHT22").device_name("温湿度传感器").device_type(3).build();
		SensorData data = SensorData.builder().data_no("9u3y1658a92g753").temperature(29.5D).humidity(68.3D).build();
		device.setData(data);
		map.put("380", device);

		try {
			//return map.get("380").toString();
			return MAPPER.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			log.info("json转换异常:{}", e);
		}
		return null;

	}

}
