/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com   
 * @author: Frankjiu
 * @date: 2020年6月4日
 * @version: V1.0
 */

package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.entity.HttpResult;
import com.service.HttpApiService;

/**
 * @Description: LogJunitTest
 * @author: Frankjiu
 * @date: 2020年6月4日
 */
/*@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration*/
public class LogJunitTest {

	private static Logger logger = Logger.getLogger(LogJunitTest.class);

	private static final String ENCODE = "UTF-8";

	@Autowired
	HttpApiService httpApiService;

	@Autowired
	private CloseableHttpClient httpClient;

	@Autowired
	private RequestConfig config;

	/**
	 * httpclient测试Post批量插入数据
	 */
	@Test
	@SuppressWarnings("resource")
	public void testBatchInsert() throws Exception {
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000)
				.setConnectionRequestTimeout(10000).build();
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://localhost:6002/log");
		post.setEntity(null);
		post.setConfig(requestConfig);
		HttpResponse response = client.execute(post);

		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity resEntity = response.getEntity();
			String message = EntityUtils.toString(resEntity, "utf-8");
			logger.info(message);
		} else {
			logger.info("请求失败!");
		}
	}

	/**
	 * 测试Get获取列表数据
	 */
	//@Test
	public void testGetList() {
		/**
		 * String dataStr = doGet("http://www.baidu.com");
		 */
		String dataStr = doGet("http://localhost:6002/log");
		logger.info(dataStr);
	}

	/**
	 * httpclient测试Post
	 */
	//@Test
	public void testInsert() {
		HttpResult result = doPost("");
		if (result.getCode() == 200) {
			String body = result.getBody();
			logger.info(body);
		} else {
			logger.info("error!");
		}
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
