/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.service   
 * @author: Frankjiu
 * @date: 2020年6月5日
 * @version: V1.0
 */

package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.HttpResult;

/**
 * @Description: HttpApiService
 * @author: Frankjiu
 * @date: 2020年6月5日
 */
@Service
public class HttpApiService {

	private static Logger logger = Logger.getLogger(HttpApiService.class);

	private static final String ENCODE = "UTF-8";

	@Autowired
	private CloseableHttpClient httpClient;

	@Autowired
	private RequestConfig config;

	public HttpApiService() {
		super();
	}

	public HttpApiService(CloseableHttpClient httpClient, RequestConfig config) {
		super();
		this.httpClient = httpClient;
		this.config = config;
	}

	/**
	 * 不带参Get请求,如果状态码为200返回body,否则返回null
	 */
	public String doGet(String url) {
		try {
			HttpGet httpGet = new HttpGet(url);
			httpGet.setConfig(config);
			// 发起请求
			CloseableHttpResponse response = this.httpClient.execute(httpGet);
			// 判断状态码是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				// 返回响应体的内容
				return EntityUtils.toString(response.getEntity(), ENCODE);
			}
		} catch (Exception e) {
			logger.info(e);
		}
		return "";
	}

	/**
	 * 不带参Post请求
	 */
	public HttpResult doPost(String url) {
		return this.doPost(url, null);
	}

	/**
	 * 带参Post请求
	 */
	public HttpResult doPost(String url, Map<String, Object> map) {
		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setConfig(config);
			// 构造表单实体对象
			if (map != null) {
				List<NameValuePair> list = new ArrayList<>();
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
				}
				UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, ENCODE);
				httpPost.setEntity(urlEncodedFormEntity);
			}
			// 发起请求
			CloseableHttpResponse response = this.httpClient.execute(httpPost);
			return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(response.getEntity(), ENCODE));
		} catch (Exception e) {
			logger.info(e);
			return new HttpResult();
		}
	}

}
