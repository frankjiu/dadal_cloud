/**
 * All rights Reserved, Designed By www.xcompany.com
 * 
 * @Package: com.modules.okhttp3
 * @author: Frankjiu
 * @date: 2020年8月19日
 * @version: V1.0
 */

package com.modules.http.okhttp3;

/**
 * @Description: 配置OkHttpClient 工具类中已配置过则此处可省略
 * @author: Frankjiu
 * @date: 2020年8月19日
 */
/*@Configuration
public class OkHttp3Config {

    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static final int CONNECT_TIMEOUT = 10; // 连接超时时间
    private static final int READ_TIMEOUT = 15; // 读取超时时间
    private static final int WRITE_TIMEOUT = 10; // 写入超时时间

    @Bean
    public OkHttpClient getOkHttpClient() {
        return okHttpClient = okHttpClient.newBuilder().connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS).writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS).build();
    }
}*/
