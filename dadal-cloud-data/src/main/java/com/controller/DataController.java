/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.controller   
 * @author: Frankjiu
 * @date: 2020年7月23日
 * @version: V1.0
 */

package com.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.zip.Deflater;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipParameters;
import org.apache.commons.io.IOUtils;
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
	private static final Map<String, Device> DATA = Maps.newHashMap();

	/**
	 * 创建测试数据
	 */
	public Map<String, Device> makeData(Map<String, Device> map) {
		map.clear();
		Device device = Device.builder().device_no("DHT22").device_name("温湿度传感器").device_type(3).build();
		SensorData data = SensorData.builder().data_no("9u3y1658a92g753").temperature(29.5D).humidity(68.3D).build();
		device.setData(data);

		for (int i = 1; i <= 2000; i++) {
			map.put("DTS000" + String.valueOf(i), device);
		}
		return map;
	}

	@GetMapping("/mqdata")
	public String getData() {
		makeData(DATA);
		try {
			long begin = System.currentTimeMillis();
			String result = MAPPER.writeValueAsString(DATA);
			long end = System.currentTimeMillis();
			log.info("json数据响应耗时:" + (end - begin));
			return result;
		} catch (JsonProcessingException e) {
			log.info("json转换异常:{}", e);
		}
		return null;

	}

	@GetMapping("/mqdatas")
	public void getDataByGzip(HttpServletResponse resp) {
		makeData(DATA);
		try {
			gZipResult(DATA, resp);
		} catch (JsonProcessingException e) {
			log.info("json转换异常:{}", e);
		}

	}

	/**
	 * 使用gzip算法压缩返回数据
	 */
	public byte[] gZipResult(Map<String, Device> map, HttpServletResponse resp) throws JsonProcessingException {
		long begin = System.currentTimeMillis();
		resp.addHeader("Content-Encoding", "gzip");
		resp.addHeader("Content-Type", "application/json;charset=utf-8");
		byte[] bytes = MAPPER.writeValueAsBytes(map);

		GzipParameters param = new GzipParameters();
		param.setCompressionLevel(Deflater.BEST_SPEED);
		// Creates a gzip compressed output stream with the specified parameters.
		try (GzipCompressorOutputStream os = new GzipCompressorOutputStream(resp.getOutputStream(), param);
				BufferedOutputStream bos = new BufferedOutputStream(os)) {
			IOUtils.write(bytes, bos);
			long end = System.currentTimeMillis();
			log.info("使用gzip压缩并写入响应耗时:" + (end - begin));
			return bytes;
		} catch (IOException e) {
			log.info("使用gzip压缩失败!", e);
			throw new RuntimeException();
		}

	}

}
